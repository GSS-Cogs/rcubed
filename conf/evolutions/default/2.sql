-- !Ups

CREATE TABLE "table2qb_config"
(
    "group_id" VARCHAR,
    "label" VARCHAR,
    "base_url" VARCHAR,
    PRIMARY KEY ("group_id")
);

-- !Downs

DROP TABLE "table2qb_config";