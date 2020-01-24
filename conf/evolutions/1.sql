
-- DROP TABLE colums_csv; Need to identify the syntax for DROP IF EXISTS

create table colums_csv(
  title STRING,
  name STRING,
  component_attachment STRING,
  property_template value_template STRING,
  datatype value_transformation STRING,
  regex STRING,
  range STRING,
  PRIMARY KEY (name),
  --FOREIGN KEY To add in??? (PersonID) REFERENCES Persons(PersonID)
);

create table components_csv(
  Label STRING,
  Description STRING,
  Component STRING,
  Type STRING,
  Codelist STRING,
  PRIMARY KEY (Label),
  --FOREIGN KEY To add in??? (PersonID) REFERENCES Persons(PersonID)
);

-- Are there data types for XML??? Or KVP???

create table components-schema_json(
  json_dump STRING
  -- are there JSON datatypes? I do not know the Scala Play Framework - will this be used against a specific DB technology???
);
