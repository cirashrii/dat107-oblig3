DROP SCHEMA IF EXISTS oblig3 CASCADE;
CREATE SCHEMA oblig3;
SET search_path TO oblig3;
CREATE TABLE Ansatt(
                       ansattid SERIAL PRIMARY KEY,
                       brukernavn CHAR(6),
                       fornavn VARCHAR(40),
                       etternavn VARCHAR(40),
                       ansettelsedato DATE,
                       stilling VARCHAR(40),
                       maanedslonn FLOAT,
);

-- CREATE TABLE Avdeling(
--     avdelingid SERIAL PRIMARY KEY,
--     navn VARCHAR(40),
--     sjef INT,
--     FOREIGN KEY(sjef) REFERENCES Ansatt(ansattid)
-- );
--
-- ALTER TABLE Ansatt ADD COLUMN avdeling INT REFERENCES Avdeling(avdelingid);
--
-- CREATE TABLE Prosjekt(
--     prosjektid SERIAL PRIMARY KEY,
--     navn VARCHAR(40),
--     beskrivelse VARCHAR(400)
-- );
--
-- CREATE TABLE Prosjektdeltagelse(
--     ansattid INT,
--     prosjektid INT,
--     PRIMARY KEY (ansattid, prosjektid),
--     rolle VARCHAR(40),
--     timetall FLOAT,
--     FOREIGN KEY(ansattid) REFERENCES Ansatt(ansattid),
--     FOREIGN KEY(prosjektid) REFERENCES Prosjekt(prosjektid)
-- );

