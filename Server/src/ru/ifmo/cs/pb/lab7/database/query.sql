CREATE TABLE users (
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users(username, password)
VALUES('s289307', 'f7753ce11bf234708c22de5402a388678d3e67a0');

CREATE TABLE laboratory (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    minimal_point DECIMAL NOT NULL,
    person_quality_min FLOAT NOT NULL,
    tuned_in_works INT NOT NULL,
    difficulty VARCHAR(15) NULL,

    username VARCHAR(255) NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE
);

CREATE TABLE coordinates (
    x BIGINT NOT NULL,
    y FLOAT NOT NULL,
    lab_id INT NOT NULL,
    FOREIGN KEY (lab_id) REFERENCES laboratory(id) ON DELETE CASCADE
);

Create TABLE discipline (
    dis_name VARCHAR(255) NOT NULL,
    self_study_hours INT NULL,
    labs_count BIGINT NULL,
    lab_id INT NOT NULL,
    FOREIGN KEY (lab_id) REFERENCES laboratory(id) ON DELETE CASCADE
);
