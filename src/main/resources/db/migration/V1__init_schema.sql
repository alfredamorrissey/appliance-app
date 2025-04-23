-- Users table
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Appliances table
CREATE TABLE appliance (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    type VARCHAR(10) NOT NULL,
    is_on BOOLEAN,          -- For Light
    speed INT,              -- For Fan (0-2)
    temperature INT,        -- For AC
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);
