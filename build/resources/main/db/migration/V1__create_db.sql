CREATE TABLE client
(
    client_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL CHECK ( LENGTH(name) >= 3 )
);

CREATE TABLE planet
(
    planet_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(500) NOT NULL CHECK ( planet_id REGEXP '^[A-Z0-9]+$' )
);

CREATE TABLE ticket
(
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    create_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    client_id INT,
    from_planet_id VARCHAR(50),
    to_planet_id VARCHAR(50),
    FOREIGN KEY (client_id) REFERENCES client(client_id) ON DELETE CASCADE,
    FOREIGN KEY (from_planet_id) REFERENCES planet(planet_id) ON DELETE CASCADE,
    FOREIGN KEY (to_planet_id) REFERENCES planet(planet_id) ON DELETE CASCADE
);