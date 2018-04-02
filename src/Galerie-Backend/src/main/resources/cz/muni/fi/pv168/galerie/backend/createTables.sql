CREATE TABLE "AGENT" (
    "ID" BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "BORN" DATE,
    "LEVEL" VARCHAR(255),
    "NAME" VARCHAR(255)
);

CREATE TABLE "MISSION" (
    "ID" BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "START" DATE NOT NULL,
    "END" DATE,
    "DESCRIPTION" VARCHAR(255) NOT NULL,
    "CODENAME" VARCHAR(255) NOT NULL,
    "LOCATION" VARCHAR(63) NOT NULL
);

CREATE TABLE "MISSION_ASSIGNMENT" (
    "ID" BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "MISSION_ID" BIGINT NOT NULL,
    "AGENT_ID" BIGINT NOT NULL
);
