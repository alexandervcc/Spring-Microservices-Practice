INSERT INTO
  REGION (id, name)
VALUES
  (1, 'Sudamerica');

INSERT INTO
  REGION (id, name)
VALUES
  (2, 'Centroamerica');

INSERT INTO
  REGION (id, name)
VALUES
  (3, 'Norteamerica');

INSERT INTO
  REGION (id, name)
VALUES
  (4, 'Europa');

INSERT INTO
  REGION (id, name)
VALUES
  (5, 'Asia');

INSERT INTO
  REGION (id, name)
VALUES
  (6, 'Africa');

INSERT INTO
  REGION (id, name)
VALUES
  (7, 'Oceanía');

INSERT INTO
  REGION (id, name)
VALUES
  (8, 'Antártida');

INSERT INTO
  CUSTOMER(
    id,
    ci,
    first_name,
    last_name,
    email,
    fk_region,
    state
  )
VALUES
(
    1,
    '1712345678',
    'Andres',
    'Guzmán',
    'mijotron@gmail.com',
    1,
    'CREATED'
  );