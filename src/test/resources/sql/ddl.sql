CREATE TABLE IF NOT EXISTS city_names
(
  id BIGINT(20) PRIMARY KEY NOT NULL,
  city_index INT(11),
  city_ukr_name VARCHAR(255),
  feature_codes VARCHAR(255),
  city_inter_name VARCHAR(255),
  latitude DOUBLE,
  longitude DOUBLE,
  population VARCHAR(255),
  region_ukr_name VARCHAR(255),
  reg_id VARCHAR(255),
  region_inter_name VARCHAR(255)
);