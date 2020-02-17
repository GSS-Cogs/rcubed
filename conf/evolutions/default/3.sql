-- !Ups
ALTER TABLE "columns" ADD "group_id" VARCHAR NOT NULL;
ALTER TABLE "columns" DROP PRIMARY KEY;
ALTER TABLE "columns" ADD PRIMARY KEY ("name", "group_id");
-- !Downs
ALTER TABLE "columns" DROP COLUMN "group_id";
ALTER TABLE "columns" DROP PRIMARY KEY;
ALTER TABLE "columns" ADD PRIMARY KEY ("name");
