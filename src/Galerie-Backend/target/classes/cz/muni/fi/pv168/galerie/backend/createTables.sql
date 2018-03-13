CREATE TABLE "GRAVE" (
    "ID" BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "COL" INTEGER NOT NULL,
    "ROW" INTEGER NOT NULL,
    "CAPACITY" INTEGER NOT NULL,
    "NOTE" VARCHAR(255)
);

CREATE TABLE "BODY" (
    "ID" BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "GRAVEID" BIGINT REFERENCES GRAVE (ID),
    "NAME" VARCHAR(255) NOT NULL,
    "GENDER" VARCHAR(6) NOT NULL,
    "BORN" DATE,
    "DIED" DATE,
    "VAMPIRE" SMALLINT NOT NULL
);