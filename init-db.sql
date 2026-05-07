-- ─────────────────────────────────────────────────────────────────────────────
-- GYM Management System — Database Initialization Script
-- Runs automatically on first MySQL container startup.
-- Each microservice owns its own isolated database (Database-per-Service pattern).
-- ─────────────────────────────────────────────────────────────────────────────

CREATE DATABASE IF NOT EXISTS gym_auth_db         CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS gym_member_db       CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS gym_subscription_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS gym_equipment_db    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS gym_attendance_db   CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Grant the application user full access to all service databases
GRANT ALL PRIVILEGES ON gym_auth_db.*         TO 'gym_user'@'%';
GRANT ALL PRIVILEGES ON gym_member_db.*       TO 'gym_user'@'%';
GRANT ALL PRIVILEGES ON gym_subscription_db.* TO 'gym_user'@'%';
GRANT ALL PRIVILEGES ON gym_equipment_db.*    TO 'gym_user'@'%';
GRANT ALL PRIVILEGES ON gym_attendance_db.*   TO 'gym_user'@'%';

FLUSH PRIVILEGES;
