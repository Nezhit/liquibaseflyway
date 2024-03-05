DO $$
    BEGIN
        IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'optoviy') THEN
            CREATE DATABASE optoviy;
        END IF;
    END
$$;
