DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public AUTHORIZATION postgres;
GRANT
ALL
ON SCHEMA public TO PUBLIC;
GRANT ALL
ON SCHEMA public TO postgres;