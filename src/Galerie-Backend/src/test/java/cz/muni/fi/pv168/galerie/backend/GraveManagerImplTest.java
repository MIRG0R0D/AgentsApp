package cz.muni.fi.pv168.galerie.backend;

import cz.muni.fi.pv168.common.*;
import java.sql.SQLException;
import javax.sql.DataSource;

import cz.muni.fi.pv168.galerie.common.DBUtils;
import cz.muni.fi.pv168.galerie.common.IllegalEntityException;
import cz.muni.fi.pv168.galerie.common.ServiceFailureException;
import cz.muni.fi.pv168.galerie.common.ValidationException;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

//------------------------------------------------------------------------------
// IMPORTANT NOTE:
// This test contains lots of comments to help you understand well all
// implementation details. You are not expected to use such kind of comments
// in your tests.
//------------------------------------------------------------------------------

/**
 * Example test class for {@link PhotoManagerImpl}.
 *
 * @author petr.adamek@bilysklep.cz
 */
public class GraveManagerImplTest {

    private PhotoManagerImpl manager;
    private DataSource ds;

    // ExpectedException is one possible mechanisms for testing if expected
    // exception is thrown. See createGraveWithExistingId() for usage example.
    @Rule
    // attribute annotated with @Rule annotation must be public :-(
    public ExpectedException expectedException = ExpectedException.none();

    //--------------------------------------------------------------------------
    // Test initialization
    //--------------------------------------------------------------------------

    private static DataSource prepareDataSource() throws SQLException {
        EmbeddedDataSource ds = new EmbeddedDataSource();
        // we will use in memory database
        ds.setDatabaseName("memory:gravemgr-test");
        // database is created automatically if it does not exist yet
        ds.setCreateDatabase("create");
        return ds;
    }

    @Before
    public void setUp() throws SQLException {
        ds = prepareDataSource();
        DBUtils.executeSqlScript(ds,PhotoManager.class.getResource("createTables.sql"));
        manager = new PhotoManagerImpl();
        manager.setDataSource(ds);
    }

    @After
    public void tearDown() throws SQLException {
        // Drop tables after each test
        DBUtils.executeSqlScript(ds,PhotoManager.class.getResource("dropTables.sql"));
    }

    //--------------------------------------------------------------------------
    // Preparing test data
    //--------------------------------------------------------------------------

    // We will need to create some Photo instances for testing purposes. We
    // could create constructor or helper method for initializing all fields,
    // but this is not well readable, especially for cases with multiple
    // parameters of the same type:
    //
    // Photo grave = new Photo(12,13,1,"Small Photo"); // constructoor
    // Photo grave = newGrave(12,13,1,"Small Photo");  // helper method
    //
    // To understand this code, you need to know or look, what is the order and
    // meaning of parameters. And it will be difficult to maintain the code when
    // some new attributes are introduced. Another option is to use set methods:
    //
    // Photo grave = new Photo();
    // grave.setColumn(12);
    // grave.setRow(13);
    // grave.setCapacity(1);
    // grave.setNote("Small Photo");
    //
    // This is better understandable, but it needs too much code to construct
    // the object. Alternative solution is to use Builder pattern:
    //
    // Photo grave = new GraveBuilder().column(12).row(13).note("Small Photo").build();
    //
    // Advantage of builder pattern is compact syntax based on fluent API,
    // clear assigment of values to attribute names and flexibility allowing to
    // set only arbitrary subset of attributes (and keeping default values for
    // others). Disadvantage of builder pattern is the need to create and
    // maintain builder class. See Item 2 in Effective Java from Joshua Bloch
    // for more details.
    //
    // To make creation of test objects even easier, we can prepare some
    // pre-configured builders with some reasonable default attribute values
    // (see sampleSmallGraveBuilder() and sampleBigGraveBuilder() bellow).
    // These values can be changed by subsequent calls of appropriate builder
    // method if needed.
    //
    // Photo graveWithExistingId = sampleSmallGraveBuilder().id(12L).build();
    //
    // This mechanism allows us to focus only to attributes important for given
    // test and use some universal reasonable value for other attribute. For
    // example, we don't need to use some specific attribute values for
    // createGrave() test, this test works well with any valid values.

    private GraveBuilder sampleSmallGraveBuilder() {
        return new GraveBuilder()
                .id(null)
                .column(12)
                .row(13)
                .capacity(1)
                .note("Small Photo");
    }

    private GraveBuilder sampleBigGraveBuilder() {
        return new GraveBuilder()
                .id(null)
                .column(22)
                .row(27)
                .capacity(6)
                .note("Big Photo");
    }

    //--------------------------------------------------------------------------
    // Tests for operations for creating and fetching graves
    //--------------------------------------------------------------------------

    @Test
    public void createGrave() {
        Photo grave = sampleSmallGraveBuilder().build();
        manager.createGrave(grave);

        Long graveId = grave.getId();
        assertThat(graveId).isNotNull();

        assertThat(manager.getGrave(graveId))
                .isNotSameAs(grave)
                .isEqualToComparingFieldByField(grave);
    }

    @Test
    public void findAllGraves() {

        assertThat(manager.findAllGraves()).isEmpty();

        Photo g1 = sampleSmallGraveBuilder().build();
        Photo g2 = sampleBigGraveBuilder().build();

        manager.createGrave(g1);
        manager.createGrave(g2);

        assertThat(manager.findAllGraves())
                .usingFieldByFieldElementComparator()
                .containsOnly(g1,g2);
    }

    // Test exception with expected parameter of @Test annotation
    // it does not allow to specify exact place where the exception
    // is expected, therefor it is suitable only for simple single line tests
    @Test(expected = IllegalArgumentException.class)
    public void createNullGrave() {
        manager.createGrave(null);
    }

    // Test exception with ExpectedException @Rule
    @Test
    public void createGraveWithExistingId() {
        Photo grave = sampleSmallGraveBuilder().id(1L).build();
        expectedException.expect(IllegalEntityException.class);
        manager.createGrave(grave);
    }

    // Test exception using AssertJ assertThatThrownBy() method
    // this requires Java 8 due to using lambda expression
    @Test
    public void createGraveWithNegativeColumn() {
        Photo grave = sampleSmallGraveBuilder().column(-1).build();
        assertThatThrownBy(() -> manager.createGrave(grave))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    public void createGraveWithNegativeRow() {
        Photo grave = sampleSmallGraveBuilder().row(-1).build();
        expectedException.expect(ValidationException.class);
        manager.createGrave(grave);
    }

    @Test
    public void createGraveWithNegativeCapacity() {
        Photo grave = sampleSmallGraveBuilder().capacity(-1).build();
        expectedException.expect(ValidationException.class);
        manager.createGrave(grave);
    }

    @Test
    public void createGraveWithZeroCapacity() {
        Photo grave = sampleSmallGraveBuilder().capacity(0).build();
        expectedException.expect(ValidationException.class);
        manager.createGrave(grave);
    }

    @Test
    public void createGraveWithZeroColumn() {
        Photo grave = sampleSmallGraveBuilder().column(0).build();
        manager.createGrave(grave);

        assertThat(manager.getGrave(grave.getId()))
                .isNotNull()
                .isEqualToComparingFieldByField(grave);
    }

    @Test
    public void createGraveWithZeroRow() {
        Photo grave = sampleSmallGraveBuilder().row(0).build();
        manager.createGrave(grave);

        assertThat(manager.getGrave(grave.getId()))
                .isNotNull()
                .isEqualToComparingFieldByField(grave);
    }

    @Test
    public void createGraveWithNullNote() {
        Photo grave = sampleSmallGraveBuilder().note(null).build();
        manager.createGrave(grave);

        assertThat(manager.getGrave(grave.getId()))
                .isNotNull()
                .isEqualToComparingFieldByField(grave);
    }

    //--------------------------------------------------------------------------
    // Tests for PhotoManager.updateGrave(Photo) operation
    //--------------------------------------------------------------------------

    @Test
    public void updateGraveColumn() {
        // Let us create two graves, one will be used for testing the update
        // and another one will be used for verification that other objects are
        // not affected by update operation
        Photo graveForUpdate = sampleSmallGraveBuilder().build();
        Photo anotherGrave = sampleBigGraveBuilder().build();
        manager.createGrave(graveForUpdate);
        manager.createGrave(anotherGrave);

        // Performa the update operation ...
        graveForUpdate.setColumn(1);

        // ... and save updated grave to database
        manager.updateGrave(graveForUpdate);

        // Check if grave was properly updated
        assertThat(manager.getGrave(graveForUpdate.getId()))
                .isEqualToComparingFieldByField(graveForUpdate);
        // Check if updates didn't affected other records
        assertThat(manager.getGrave(anotherGrave.getId()))
                .isEqualToComparingFieldByField(anotherGrave);
    }

    // Now we want to test also other update operations. We could do it the same
    // way as in updateGraveColumn(), but we would get couple of almost the same
    // test methods, which would differ from each other only in single line
    // with update operation. To avoid duplicit code and make the test better
    // maintainable, we need to separate the update operation from the test
    // method and to let us call test method multiple times with differen
    // update operation.

    // Let start with functional interface which will represent update operation
    // BTW, we could use standard Consumer<T> functional interface (and I would
    // probably do it in real test), but I decided to define my own interface to
    // make the test better understandable
    @FunctionalInterface
    private static interface Operation<T> {
        void callOn(T subjectOfOperation);
    }

    // The next step is implementation of generic test method. This method will
    // perform update test with given update operation.
    // The method is almost the same as updateGraveColumn(), the only difference
    // is the line with calling given updateOperation.
    private void testUpdateGrave(Operation<Photo> updateOperation) {
        Photo sourceGrave = sampleSmallGraveBuilder().build();
        Photo anotherGrave = sampleBigGraveBuilder().build();
        manager.createGrave(sourceGrave);
        manager.createGrave(anotherGrave);

        updateOperation.callOn(sourceGrave);

        manager.updateGrave(sourceGrave);
        assertThat(manager.getGrave(sourceGrave.getId()))
                .isEqualToComparingFieldByField(sourceGrave);
        // Check if updates didn't affected other records
        assertThat(manager.getGrave(anotherGrave.getId()))
                .isEqualToComparingFieldByField(anotherGrave);
    }

    // Now we will call testUpdateGrave(...) method with different update
    // operations. Update operation is defined with Lambda expression.

    @Test
    public void updateGraveRow() {
        testUpdateGrave((grave) -> grave.setRow(3));
    }

    @Test
    public void updateGraveCapacity() {
        testUpdateGrave((grave) -> grave.setCapacity(5));
    }

    @Test
    public void updateGraveNote() {
        testUpdateGrave((grave) -> grave.setNote("Not so nice grave"));
    }

    @Test
    public void updateGraveNoteToNull() {
        testUpdateGrave((grave) -> grave.setNote(null));
    }

    // Test also if attemtpt to call update with invalid grave throws
    // the correct exception.

    @Test(expected = IllegalArgumentException.class)
    public void updateNullGrave() {
        manager.updateGrave(null);
    }

    @Test
    public void updateGraveWithNullId() {
        Photo grave = sampleSmallGraveBuilder().id(null).build();
        expectedException.expect(IllegalEntityException.class);
        manager.updateGrave(grave);
    }

    @Test
    public void updateGraveWithNonExistingId() {
        Photo grave = sampleSmallGraveBuilder().id(1L).build();
        expectedException.expect(IllegalEntityException.class);
        manager.updateGrave(grave);
    }

    @Test
    public void updateGraveWithNegativeColumn() {
        Photo grave = sampleSmallGraveBuilder().build();
        manager.createGrave(grave);
        grave.setColumn(-1);
        expectedException.expect(ValidationException.class);
        manager.updateGrave(grave);
    }

    @Test
    public void updateGraveWithNegativeRow() {
        Photo grave = sampleSmallGraveBuilder().build();
        manager.createGrave(grave);
        grave.setRow(-1);
        expectedException.expect(ValidationException.class);
        manager.updateGrave(grave);
    }

    @Test
    public void updateGraveWithZeroCapacity() {
        Photo grave = sampleSmallGraveBuilder().build();
        manager.createGrave(grave);
        grave.setCapacity(0);
        expectedException.expect(ValidationException.class);
        manager.updateGrave(grave);
    }

    @Test
    public void updateGraveWithNegativeCapacity() {
        Photo grave = sampleSmallGraveBuilder().build();
        manager.createGrave(grave);
        grave.setCapacity(-1);
        expectedException.expect(ValidationException.class);
        manager.updateGrave(grave);
    }

    //--------------------------------------------------------------------------
    // Tests for PhotoManager.deleteGrave(Photo) operation
    //--------------------------------------------------------------------------

    @Test
    public void deleteGrave() {

        Photo g1 = sampleSmallGraveBuilder().build();
        Photo g2 = sampleBigGraveBuilder().build();
        manager.createGrave(g1);
        manager.createGrave(g2);

        assertThat(manager.getGrave(g1.getId())).isNotNull();
        assertThat(manager.getGrave(g2.getId())).isNotNull();

        manager.deleteGrave(g1);

        assertThat(manager.getGrave(g1.getId())).isNull();
        assertThat(manager.getGrave(g2.getId())).isNotNull();

    }

    // Test also if attemtpt to call delete with invalid parameter throws
    // the correct exception.

    @Test(expected = IllegalArgumentException.class)
    public void deleteNullGrave() {
        manager.deleteGrave(null);
    }

    @Test
    public void deleteGraveWithNullId() {
        Photo grave = sampleSmallGraveBuilder().id(null).build();
        expectedException.expect(IllegalEntityException.class);
        manager.deleteGrave(grave);
    }

    @Test
    public void deleteGraveWithNonExistingId() {
        Photo grave = sampleSmallGraveBuilder().id(1L).build();
        expectedException.expect(IllegalEntityException.class);
        manager.deleteGrave(grave);
    }

    //--------------------------------------------------------------------------
    // Tests if PhotoManager methods throws ServiceFailureException in case of
    // DB operation failure
    //--------------------------------------------------------------------------

    @Test
    public void createGraveWithSqlExceptionThrown() throws SQLException {
        // Create sqlException, which will be thrown by our DataSource mock
        // object to simulate DB operation failure
        SQLException sqlException = new SQLException();
        // Create DataSource mock object
        DataSource failingDataSource = mock(DataSource.class);
        // Instruct our DataSource mock object to throw our sqlException when
        // DataSource.getConnection() method is called.
        when(failingDataSource.getConnection()).thenThrow(sqlException);
        // Configure our manager to use DataSource mock object
        manager.setDataSource(failingDataSource);

        // Create Photo instance for our test
        Photo grave = sampleSmallGraveBuilder().build();

        // Try to call Manager.createGrave(Photo) method and expect that
        // exception will be thrown
        assertThatThrownBy(() -> manager.createGrave(grave))
                // Check that thrown exception is ServiceFailureException
                .isInstanceOf(ServiceFailureException.class)
                // Check if cause is properly set
                .hasCause(sqlException);
    }

    // Now we want to test also other methods of PhotoManager. To avoid having
    // couple of method with lots of duplicit code, we will use the similar
    // approach as with testUpdateGrave(Operation) method.

    private void testExpectedServiceFailureException(Operation<PhotoManager> operation) throws SQLException {
        SQLException sqlException = new SQLException();
        DataSource failingDataSource = mock(DataSource.class);
        when(failingDataSource.getConnection()).thenThrow(sqlException);
        manager.setDataSource(failingDataSource);
        assertThatThrownBy(() -> operation.callOn(manager))
                .isInstanceOf(ServiceFailureException.class)
                .hasCause(sqlException);
    }

    @Test
    public void updateGraveWithSqlExceptionThrown() throws SQLException {
        Photo grave = sampleSmallGraveBuilder().build();
        manager.createGrave(grave);
        testExpectedServiceFailureException((graveManager) -> graveManager.updateGrave(grave));
    }

    @Test
    public void getGraveWithSqlExceptionThrown() throws SQLException {
        Photo grave = sampleSmallGraveBuilder().build();
        manager.createGrave(grave);
        testExpectedServiceFailureException((graveManager) -> graveManager.getGrave(grave.getId()));
    }

    @Test
    public void deleteGraveWithSqlExceptionThrown() throws SQLException {
        Photo grave = sampleSmallGraveBuilder().build();
        manager.createGrave(grave);
        testExpectedServiceFailureException((graveManager) -> graveManager.deleteGrave(grave));
    }

    @Test
    public void findAllGravesWithSqlExceptionThrown() throws SQLException {
        testExpectedServiceFailureException((graveManager) -> graveManager.findAllGraves());
    }

}
