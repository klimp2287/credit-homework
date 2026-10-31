CREATE TABLE credit_application (
    id BIGSERIAL PRIMARY KEY,
    amount NUMERIC(19, 2) NOT NULL,
    term_months INT NOT NULL,
     monthly_income NUMERIC(19, 2) NOT NULL,
     current_credit_load NUMERIC(19, 2) NOT NULL,
     credit_rating INT NOT NULL,
     status VARCHAR(20) NOT NULL DEFAULT 'IN_PROCESS',
     created_at TIMESTAMP NOT NULL DEFAULT NOW()
);