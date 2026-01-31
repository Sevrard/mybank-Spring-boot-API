CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       firstname VARCHAR(255) NOT NULL,
                       lastname VARCHAR(255) NOT NULL
);

CREATE TABLE user_credentials (
                                  user_id UUID PRIMARY KEY,
                                  password_hash VARCHAR(255) NOT NULL,
                                  enabled BOOLEAN NOT NULL,
                                  role VARCHAR(50) NOT NULL,
                                  CONSTRAINT fk_user_credentials_user
                                      FOREIGN KEY (user_id)
                                          REFERENCES users(id)
);

CREATE TABLE accounts (
                          id UUID PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          user_id UUID NOT NULL,
                          balance NUMERIC(15,2) NOT NULL,
                          CONSTRAINT fk_accounts_user
                              FOREIGN KEY (user_id)
                                  REFERENCES users(id)
);

CREATE TABLE transactions (
                              id UUID PRIMARY KEY,
                              account_id UUID NOT NULL,
                              amount NUMERIC(15,2) NOT NULL,
                              type VARCHAR(50) NOT NULL,
                              created_at TIMESTAMP NOT NULL,
                              CONSTRAINT fk_transactions_account
                                  FOREIGN KEY (account_id)
                                      REFERENCES accounts(id)
);
