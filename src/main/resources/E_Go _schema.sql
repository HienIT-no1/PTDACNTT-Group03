DROP DATABASE IF EXISTS E_Go;
CREATE DATABASE E_Go CHARACTER SET = 'utf8mb4' COLLATE = 'utf8mb4_unicode_ci';
USE E_Go;
-- ---
-- Bảng 1: Accounts (Tài khoản Quản trị/Giảng viên)
-- ---
-- Bảng này CHỈ lưu tài khoản cho Admin và Teacher.
CREATE TABLE Accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    `role` ENUM('TEACHER', 'ADMIN') NOT NULL,
    avatar_url VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---
-- Bảng 2: Consultations (Yêu cầu tư vấn)
-- ---
-- Bảng này lưu thông tin từ khách (guest) khi họ yêu cầu tư vấn.
CREATE TABLE Consultations (
    consultation_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(255) NULL,
    message TEXT NULL,
    `status` ENUM('NEW', 'CONTACTED') NOT NULL DEFAULT 'NEW',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---
-- Bảng 3: Teacher_Profiles (Hồ sơ giảng viên)
-- ---
-- Lưu thông tin chuyên môn mở rộng cho giảng viên (quan hệ 1-1 với Accounts).
CREATE TABLE Teacher_Profiles (
    account_id INT PRIMARY KEY,
    bio TEXT NULL,
    specialization VARCHAR(255) NULL,
    years_of_experience INT DEFAULT 0,
    is_featured BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (account_id) 
        REFERENCES Accounts(account_id)
        ON DELETE CASCADE -- Nếu xóa tài khoản, hồ sơ này cũng bị xóa
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---
-- Bảng 4: Courses (Khóa học)
-- ---
-- Bảng chính quản lý các khóa học, với cơ chế free/private.
CREATE TABLE Courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NULL,
    category VARCHAR(100) NULL,
    `format` ENUM('ONLINE', 'OFFLINE') NOT NULL,
    created_by INT NULL, -- Ai là người tạo khóa học
    thumbnail_url VARCHAR(255) NULL,
    access_type ENUM('FREE', 'PRO') NOT NULL DEFAULT 'FREE',
    access_password VARCHAR(255) NULL, -- Sẽ là NULL nếu access_type = 'free'
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (created_by) 
        REFERENCES Accounts(account_id)
        ON DELETE SET NULL -- Nếu tài khoản admin/teacher bị xóa, khóa học vẫn còn nhưng không có người tạo
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---
-- Bảng 5: Articles (Bài viết: Blog, Tin tức,...)
-- ---
-- Bảng chung cho tất cả các loại nội dung động.
CREATE TABLE Articles (
    article_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    author_id INT NULL, -- Tác giả (Admin hoặc Teacher)
    category ENUM('NEWS', 'RECRUITMENT', 'BLOG', 'LEARNING_PATH', 'LEARNING_METHOD') NOT NULL,
    `status` ENUM('DRAFT', 'PUBLISHED') NOT NULL DEFAULT 'DRAFT',
    thumbnail_url VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (author_id) 
        REFERENCES Accounts(account_id)
        ON DELETE SET NULL -- Nếu tác giả bị xóa, bài viết vẫn còn
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---
-- Bảng 6: Documents (Tài liệu)
-- ---
-- Lưu trữ thông tin về các file PDF, video, ...
CREATE TABLE Documents (
    document_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NULL,
    file_url VARCHAR(255) NOT NULL, -- Đường dẫn tới file hoặc link video
    `type` ENUM('PDF', 'VIDEO', 'WORD', 'OTHER') NOT NULL,
    uploader_id INT NULL, -- Người tải lên (Admin hoặc Teacher)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (uploader_id) 
        REFERENCES Accounts(account_id)
        ON DELETE SET NULL -- Nếu người tải lên bị xóa, tài liệu vẫn còn
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---
-- Bảng 7: Testimonials (Cảm nhận học viên)
-- ---
-- Lưu cảm nhận của học viên (không cần đăng nhập).
CREATE TABLE Testimonials (
    testimonial_id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL, -- Tên do học viên tự nhập
    course_id INT NULL, -- Khóa học họ đã tham gia (nếu có)
    content TEXT NOT NULL,
    `status` ENUM('PENDING', 'APPROVED') NOT NULL DEFAULT 'PENDING', -- Quản lý duyệt
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (course_id) 
        REFERENCES Courses(course_id)
        ON DELETE SET NULL -- Nếu xóa khóa học, cảm nhận vẫn còn nhưng không liên kết nữa
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---
-- Bảng 8: Course_Documents (Bảng nối Tài liệu - Khóa học)
-- ---
-- Kết nối khóa học nào có tài liệu nào (Quan hệ Nhiều-Nhiều).
CREATE TABLE Course_Documents (
    course_id INT NOT NULL,
    document_id INT NOT NULL,
    
    PRIMARY KEY (course_id, document_id), -- Khóa chính tổng hợp
    
    FOREIGN KEY (course_id) 
        REFERENCES Courses(course_id)
        ON DELETE CASCADE, -- Nếu xóa khóa học, liên kết này bị xóa
    
    FOREIGN KEY (document_id) 
        REFERENCES Documents(document_id)
        ON DELETE CASCADE -- Nếu xóa tài liệu, liên kết này bị xóa
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;