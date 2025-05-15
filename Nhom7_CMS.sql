CREATE DATABASE NHOM7_CMS;
GO
USE NHOM7_CMS;
GO

-- Bảng VaiTro
CREATE TABLE VaiTro (
    MaVaiTro CHAR(256) PRIMARY KEY,
    TenVaiTro CHAR(256),
    MoTa TEXT
);

-- Bảng NguoiDung
CREATE TABLE NguoiDung (
    MaNguoiDung CHAR(256) PRIMARY KEY,
    MaVaiTro CHAR(256),
    TenDangNhap CHAR(256),
    MatKhau CHAR(256),
    HoTen CHAR(256),
    Email CHAR(256),
    FOREIGN KEY (MaVaiTro) REFERENCES VaiTro(MaVaiTro)
);

-- Bảng ThongBao
CREATE TABLE ThongBao (
    MaThongBao CHAR(256) PRIMARY KEY,
    MaNguoiDung CHAR(256),
    TieuDe CHAR(256),
    NoiDung TEXT,
    TrangThai CHAR(256),
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)
);

-- Bảng SuKien
CREATE TABLE SuKien (
    MaSuKien CHAR(256) PRIMARY KEY,
    MaNguoiDung CHAR(256),
    TieuDe CHAR(256),
    MoTa TEXT,
    DuongDan CHAR(256),
    ThoiGianBatDau DATETIME,
    ThoiGianKetThuc DATETIME,
    DiaDiem CHAR(256),
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)
);

-- Bảng DanhMuc
CREATE TABLE DanhMuc (
    MaDanhMuc CHAR(256) PRIMARY KEY,
    Ten CHAR(256),
    DuongDan CHAR(256),
    MoTa TEXT
);

-- Bảng BaiViet
CREATE TABLE BaiViet (
    MaBaiViet CHAR(256) PRIMARY KEY,
    MaNguoiDung CHAR(256),
    MaDanhMuc CHAR(256),
    TieuDe CHAR(256),
    DuongDan CHAR(256),
    NoiDung TEXT,
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung),
    FOREIGN KEY (MaDanhMuc) REFERENCES DanhMuc(MaDanhMuc)
);

-- Bảng CaiDat
CREATE TABLE CaiDat (
    MaCaiDat CHAR(256) PRIMARY KEY,
    TenCaiDat CHAR(256),
    GiaTri CHAR(256)
);

-- Bảng MetaData
CREATE TABLE MetaData (
    MaMeta CHAR(256) PRIMARY KEY,
    LoaiDoiTuong CHAR(256),
    MaDoiTuong CHAR(256),
    Khoa CHAR(256),
    GiaTri CHAR(256)
);

-- Bảng Menu
CREATE TABLE Menu (
    MaMenu CHAR(256) PRIMARY KEY,
    MaNguoiDung CHAR(256),
    Ten CHAR(256),
    MenuCha CHAR(256),
    DuongDan CHAR(256),
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)
);

-- Bảng Trang
CREATE TABLE Trang (
    LoaiDoiTuong CHAR(256) PRIMARY KEY,
    MaMenu CHAR(256),
    MaNguoiDung CHAR(256),
    TieuDe CHAR(256),
    DuongDan CHAR(256),
    NoiDung TEXT,
    TrangThai CHAR(256),
    FOREIGN KEY (MaMenu) REFERENCES Menu(MaMenu),
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)
);