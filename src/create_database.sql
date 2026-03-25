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
                       maanedslonn FLOAT
);

INSERT INTO Ansatt(brukernavn, fornavn, etternavn, ansettelsedato, stilling, maanedslonn) VALUES
                                                                                              ('njo','Nils-Erik','Just-Olsen','2001-09-01','Spionsjef',0),
                                                                                              ('eoj','Erik-Nils','Olsen-Just','2001-09-10','Pilot',7500),
                                                                                              ('gus','Gudrun','Slåstad','1982-11-29','Sekretær',10000),
                                                                                              ('emu','Emanuel','Lemon','2008-07-20','Økonomiansvarlig',437),
                                                                                              ('rjfk','Robert Junior F.','Kendy','2020-03-11','Intelligence Officer',100000),
                                                                                              ('mrb','Jonny','Engelsk','1987-07-08','Spion',1987),
                                                                                              ('gjo','Gerhard','Just-Olsen Slåstad','2026-03-24','Spionsjef',0),
                                                                                              ('ff','Frank','Fredstad','2022-10-07','Teknologiansvarlig',4269),
                                                                                              ('rn','Rikard','Nickson','1972-06-17','Spion',50000);

-- HUSK Å TA MED EN ANSATT TIL!!!!!!!! --------------------------------------


CREATE TABLE Avdeling(
                         avdelingid SERIAL PRIMARY KEY,
                         navn VARCHAR(40),
                         sjef INT,
                         FOREIGN KEY(sjef) REFERENCES Ansatt(ansattid)
);

INSERT INTO Avdeling(navn, sjef)
VALUES ('Admin',1),
       ('Base',5),
       ('Field',9);


ALTER TABLE Ansatt ADD COLUMN avdeling INT REFERENCES Avdeling(avdelingid);

UPDATE Ansatt
SET avdeling = 1
WHERE ansattid = 1 OR ansattid = 3;

UPDATE Ansatt
SET avdeling = 2
WHERE ansattid = 4 OR ansattid = 5 OR ansattid = 8;

UPDATE Ansatt
SET avdeling = 3
WHERE ansattid = 2 OR ansattid = 6 OR ansattid = 7 OR ansattid = 9;

-- CREATE TABLE Prosjekt(
--                          prosjektid SERIAL PRIMARY KEY,
--                          navn VARCHAR(40),
--                          beskrivelse VARCHAR(400)
-- );
--
-- CREATE TABLE ProsjektAnsattRelasjon(
--                                        ansattid INT,
--                                        prosjektid INT,
--                                        PRIMARY KEY (ansattid, prosjektid),
--                                        rolle VARCHAR(40),
--                                        timetall FLOAT,
--                                        FOREIGN KEY(ansattid) REFERENCES Ansatt(ansattid),
--                                        FOREIGN KEY(prosjektid) REFERENCES Prosjekt(prosjektid)
-- );
