package com.goldenboy.server.common;

public class StatusConverter {
    public static String toStatusString(Byte status) {
        return switch (status) {
            case -1 -> "Đã huỷ";
            case 0 -> "Khởi tạo";
            case 1 -> "Chờ khách duyệt";
            case 2 -> "Đang sửa";
            case 3 -> "Chờ thanh toán";
            case 4 -> "Hoàn thành";
            default -> "Không xác định";
        };
    }
}
