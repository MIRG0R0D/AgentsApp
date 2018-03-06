package cz.muni.fi.pv168.agents.backend;

import java.time.LocalDate;
import java.util.Objects;


public class Agent {

    private Long id;
    private String name;
    private LocalDate lastEdit;
    private byte[] data;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastEdit(LocalDate lastEdit) {
        this.lastEdit = lastEdit;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getLastEdit() {
        return lastEdit;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Photo{"
                + "id=" + id
                + ", name=" + name
                + ", lastEdit=" + lastEdit
                + ", data=" + data
                + '}';
    }

    /**
     * Returns true if obj represents the same grave. Two objects are considered
     * to represent the same grave when both are instances of {@link Agent}
     * class, both have assigned some id and this id is the same.
     *
     *
     * @param obj the reference object with which to compare.
     * @return true if obj represents the same grave.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Agent other = (Agent) obj;
        if (obj != this && this.id == null) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }


}
