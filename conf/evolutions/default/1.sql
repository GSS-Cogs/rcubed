
-- DROP TABLE colums_csv; Need to identify the syntax for DROP IF EXISTS

-- !Ups

CREATE TABLE "columns" (
  "title" VARCHAR,
  "name" VARCHAR NOT NULL,
  "component_attachment" VARCHAR,
  "property_template" VARCHAR,
  "value_template" VARCHAR,
  "datatype" VARCHAR,
  "value_transformation" VARCHAR,
  "regex" VARCHAR,
  "range" VARCHAR,
  PRIMARY KEY ("name"),
  --FOREIGN KEY To add in??? (PersonID) REFERENCES Persons(PersonID)
);



CREATE TABLE "components" (
  "Label" VARCHAR,
  "Description" VARCHAR,
  "Component Type" VARCHAR,
  "Codelist" VARCHAR,
  PRIMARY KEY ("Label"),
  --FOREIGN KEY To add in??? (PersonID) REFERENCES Persons(PersonID)
);

-- !Downs

DROP TABLE "columns";
DROP TABLE "components";