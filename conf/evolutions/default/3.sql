-- !Ups
ALTER TABLE "columns" ADD "group_id" VARCHAR;
-- !Downs
ALTER TABLE "columns" DROP COLUMN "group_id";