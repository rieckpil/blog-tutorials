-- Create hogwarts_houses table
CREATE TABLE hogwarts_houses (
  id BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
  name VARCHAR(50) NOT NULL UNIQUE
);

-- Create wizards table
CREATE TABLE wizards (
  id BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
  name VARCHAR(50) NOT NULL,
  house_id BINARY(16) NOT NULL,
  wand_type VARCHAR(20),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT wizard_fkey_house FOREIGN KEY (house_id)
  REFERENCES hogwarts_houses (id)
);