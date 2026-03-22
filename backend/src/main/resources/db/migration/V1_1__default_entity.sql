CREATE SEQUENCE IF NOT EXISTS sporta.age_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS sporta.doc_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS sporta.role_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS sporta.user_id_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE sporta.roles (
                              id BIGINT PRIMARY KEY DEFAULT nextval('sporta.role_id_seq'),
                              name_role VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE sporta.users (
                              id BIGINT PRIMARY KEY DEFAULT nextval('sporta.user_id_seq'),
                              login VARCHAR(255) NOT NULL UNIQUE,
                              password VARCHAR(255) NOT NULL,
                              email VARCHAR(255) NOT NULL UNIQUE,
                              number_phone VARCHAR(255) NOT NULL UNIQUE,
                              data_registration TIMESTAMP NOT NULL DEFAULT now(),
                              role_id BIGINT NOT NULL,

                              CONSTRAINT fk_users_role
                                  FOREIGN KEY (role_id)
                                      REFERENCES sporta.roles (id)
);

CREATE TABLE sporta.age_categories (
                                       id BIGINT PRIMARY KEY DEFAULT nextval('sporta.age_id_seq'),
                                       name_categories VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE sporta.instructors (
                                    user_id BIGINT PRIMARY KEY,
                                    gender VARCHAR(50) NOT NULL,
                                    data_of_birth DATE NOT NULL,
                                    photo VARCHAR(255) NOT NULL,
                                    skill_description TEXT NOT NULL,
                                    certificate_number VARCHAR(255) NOT NULL UNIQUE,
                                    last_name VARCHAR(255) NOT NULL,
                                    first_name VARCHAR(255) NOT NULL,
                                    middle_name VARCHAR(255),

                                    CONSTRAINT fk_instructor_user
                                        FOREIGN KEY (user_id)
                                            REFERENCES sporta.users (id)
                                            ON DELETE CASCADE
);

CREATE TABLE sporta.students (
                                 user_id BIGINT PRIMARY KEY,
                                 gender VARCHAR(50) NOT NULL,
                                 data_of_birth DATE NOT NULL,
                                 photo VARCHAR(255),
                                 last_name VARCHAR(255) NOT NULL,
                                 first_name VARCHAR(255) NOT NULL,
                                 middle_name VARCHAR(255),

                                 CONSTRAINT fk_students_user
                                     FOREIGN KEY (user_id)
                                         REFERENCES sporta.users (id)
                                         ON DELETE CASCADE
);

CREATE TABLE sporta.documents (
                                  id BIGINT PRIMARY KEY DEFAULT nextval('sporta.doc_id_seq'),
                                  user_doc_id BIGINT NOT NULL,
                                  doc VARCHAR(255) NOT NULL,

                                  CONSTRAINT fk_documents_instructor
                                      FOREIGN KEY (user_doc_id)
                                          REFERENCES sporta.users (id)
                                          ON DELETE CASCADE
);

CREATE TABLE sporta.sport_types (
                                    id CHAR(11) PRIMARY KEY,
                                    name_types_sport VARCHAR(255) NOT NULL
);

CREATE TABLE sporta.sport_disciplines (
                                          id CHAR(11) PRIMARY KEY,
                                          id_sport_type CHAR(11),
                                          name_sport_disciplines VARCHAR(255) NOT NULL,

                                          CONSTRAINT fk_disciplines_type
                                              FOREIGN KEY (id_sport_type)
                                                  REFERENCES sporta.sport_types (id)
                                                  ON DELETE CASCADE

);
