<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 10/10/2024
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>
                    Thông tin tòa nhà
                </h1>
            </div><!-- /.page-header -->

            <!--Chia phần nội dung thành 12 cột-->
            <!--Thông tin tòa nhà-->
            <div class="row">
                <div class="col-xs-12">
                    <form:form method="get" id="form-edit" modelAttribute="buildingEdit" action="/admin/building-edit">
                        <!--name building-->
                        <div class="form-group">
                            <label class="col-xs-3">Tên tòa nhà</label>
                            <div class="col-xs-9">
                                <!--"name" chính là key khi gửi về dưới dạng JSON
                                                                 class="form-control" giúp cái ô textbox tràn ra vừa diện tích của nó
                                                            + placeholder là cái gợi ý (giống cái "Type here to search" trên windows 10 ấy)-->

                                    <%--                                                        <input type="text" name="name" class="form-control"--%>
                                    <%--                                                               placeholder="Gõ tên tòa nhà..."--%>
                                    <%--                                                               value="${modelSearch.name}">--%>
                                <!--Dùng form:input hay hơn input thường vì:
                                    + ko cần set kiểu dữ liệu (kiểu dữ liệu sẽ tự biến đổi)
                                    + ko cần set "name" và "value" -->
                                <form:input class="form-control"
                                            placeholder="Gõ tên tòa nhà..." path="name"/>
                            </div>
                        </div>

                        <!--district id-->
                        <div class="form-group">
                            <label class="col-xs-3">Quận</label>
                            <div class="col-xs-3">
                                <form:select path="district" class="form-control"> <!--Cái path này liên quan đến bên BuildingSearchRequest-->
                                    <form:option value="" label="--Chọn quận--"/>
                                    <form:options items="${district}"/>
                                </form:select>
                            </div>
                        </div>

                        <!--ward-->
                        <div class="form-group">
                            <label class="col-xs-3">Phường</label>
                            <div class="col-xs-9">
                                    <%--                                                        <input type="text" name="ward" class="form-control"--%>
                                    <%--                                                               value="${modelSearch.ward}">--%>
                                <form:input path="ward" class="form-control"/>
                            </div>
                        </div>

                        <!--street-->
                        <div class="form-group">
                            <label class="col-xs-3">Đường</label>
                            <div class="col-xs-9">
                                    <%--                                                        <input type="text" name="street" class="form-control"--%>
                                    <%--                                                               value="${modelSearch.street}">--%>
                                <form:input class="form-control" path="street"/>
                            </div>
                        </div>

                        <!--structure-->
                        <div class="form-group">
                            <label class="col-xs-3">Cấu trúc</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="structure" id="structure" class="form-control">--%>
                                <form:input path="structure" class="form-control"/>
                            </div>
                        </div>

                        <!--number of basement-->
                        <div class="form-group">
                            <label class="col-xs-3">Số tầng hầm</label>
                            <div class="col-xs-9">
                                <input type="number" name="numberOfBasement" id="numberOfBasement"
                                       class="form-control">
                            </div>
                        </div>

                        <!--floor area-->
                        <div class="form-group">
                            <label class="col-xs-3">Diện tích sàn</label>
                            <div class="col-xs-9">
                                <input type="number" name="floorArea" id="floorArea" class="form-control">
                            </div>
                        </div>

                        <!--type code-->
                        <div class="form-group">
                            <label class="col-xs-3">Loại tòa nhà</label>
                            <div class="col-xs-9">
                                <!--Để id giống nhau và type là radio thì sẽ chỉ đánh dấu được 1 cái-->
                                <form:checkboxes items="${rentType}" path="typeCode"/>
                            </div>
                        </div>

                        <!--direction-->
                        <div class="form-group">
                            <label class="col-xs-3">Hướng</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="direction" id="direction" class="form-control">--%>
                                <form:input path="direction" class="form-control"/>
                            </div>
                        </div>

                        <!--level-->
                        <div class="form-group">
                            <label class="col-xs-3">Hạng</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="level" id="level" class="form-control">--%>
                                <form:input path="level" class="form-control"/>
                            </div>
                        </div>

                        <!--rent price-->
                        <div class="form-group">
                            <label class="col-xs-3">Giá thuê</label>
                            <div class="col-xs-9">
                                <input type="number" name="rentPrice" id="rentPrice" class="form-control">
                            </div>
                        </div>

                        <!--rent price description-->
                        <div class="form-group">
                            <label class="col-xs-3">Mô tả giá thuê</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="rentPriceDescription" id="rentPriceDescription"--%>
<%--                                       class="form-control">--%>
                                <form:input path="rentPriceDescription" class="form-control"/>
                            </div>
                        </div>

                        <!--service fee-->
                        <div class="form-group">
                            <label class="col-xs-3">Phí dịch vụ</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="serviceFee" id="serviceFee" class="form-control">--%>
                                <form:input path="serviceFee" class="form-control"/>
                            </div>
                        </div>

                        <!--car fee-->
                        <div class="form-group">
                            <label class="col-xs-3">Phí ô tô</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="carFee" id="carFee" class="form-control">--%>
                                <form:input path="carFee" class="form-control"/>
                            </div>
                        </div>

                        <!--motorbike fee-->
                        <div class="form-group">
                            <label class="col-xs-3">Phí xe máy</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="motorbikeFee" id="motorbikeFee" class="form-control">--%>
                                <form:input path="motorFee" class="form-control"/>
                            </div>
                        </div>

                        <!--overtime fee-->
                        <div class="form-group">
                            <label class="col-xs-3">Phí quá giờ</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="overtimeFee" id="overtimeFee" class="form-control">--%>
                                <form:input path="overtimeFee" class="form-control"/>
                            </div>
                        </div>

                        <!--water fee-->
                        <div class="form-group">
                            <label class="col-xs-3">Tiền nước</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="waterFee" id="waterFee" class="form-control">--%>
                                <form:input path="waterFee" class="form-control"/>
                            </div>
                        </div>

                        <!--electricity fee-->
                        <div class="form-group">
                            <label class="col-xs-3">Tiền điện</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="electricityFee" id="electricityFee"--%>
<%--                                       class="form-control">--%>
                                <form:input path="electricityFee" class="form-control"/>
                            </div>
                        </div>

                        <!--deposit-->
                        <div class="form-group">
                            <label class="col-xs-3">Tiền đặt cọc</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="deposit" id="deposit" class="form-control">--%>
                                <form:input path="deposit" class="form-control"/>
                            </div>
                        </div>

                        <!--payment-->
                        <div class="form-group">
                            <label class="col-xs-3">Tiền thanh toán</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="payment" id="payment" class="form-control">--%>
                                <form:input path="payment" class="form-control"/>
                            </div>
                        </div>

                        <!--rent time-->
                        <div class="form-group">
                            <label class="col-xs-3">Thời gian thuê</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="rentTime" id="rentTime" class="form-control">--%>
                                <form:input path="rentTime" class="form-control"/>
                            </div>
                        </div>

                        <!--decoration time-->
                        <div class="form-group">
                            <label class="col-xs-3">Thời gian trang trí</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="decorationTime" id="decorationTime"--%>
<%--                                       class="form-control">--%>
                                <form:input path="decorationTime" class="form-control"/>
                            </div>
                        </div>

                        <!--brokerage fee-->
                        <div class="form-group">
                            <label class="col-xs-3">Phí môi giới</label>
                            <div class="col-xs-9">
<%--                                <input type="number" name="brokerageFee" id="brokerageFee" class="form-control">--%>
                                <form:input path="brokerageFee" class="form-control"/>
                            </div>
                        </div>

                        <!--note-->
                        <div class="form-group">
                            <label class="col-xs-3">Ghi chú</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="note" id="note" class="form-control">--%>
                                <form:input path="note" class="form-control"/>
                            </div>
                        </div>

                        <!--link of building-->
                        <div class="form-group">
                            <label class="col-xs-3">Liên kết tòa nhà</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="linkOfBuilding" id="linkOfBuilding"--%>
<%--                                       class="form-control">--%>
                                <form:input path="buildingDTOs" class="form-control"/>
                            </div>
                        </div>

                        <!--map-->
                        <div class="form-group">
                            <label class="col-xs-3">Bản đồ</label>
                            <div class="col-xs-9">
                                <input type="text" name="map" id="map" class="form-control">
                            </div>
                        </div>

                        <!--image-->
                        <div class="form-group">
                            <label class="col-xs-3">Ảnh</label>
                            <div class="col-xs-9">
                                <input type="text" name="image" id="image" class="form-control">
                            </div>
                        </div>

                        <!--created date-->
                        <div class="form-group">
                            <label class="col-xs-3">Ngày tạo</label>
                            <div class="col-xs-3">
                                <input type="datetime-local" name="createdDate" id="createdDate"
                                       class="form-control">
                            </div>
                        </div>

                        <!--modified date-->
                        <div class="form-group">
                            <label class="col-xs-3">Ngày sửa</label>
                            <div class="col-xs-3">
                                <input type="datetime-local" name="modifiedDate" id="modifiedDate"
                                       class="form-control">
                            </div>
                        </div>

                        <!--created by-->
                        <div class="form-group">
                            <label class="col-xs-3">Người tạo</label>
                            <div class="col-xs-9">
                                <input type="text" name="createdBy" id="createdBy" class="form-control">
                            </div>
                        </div>

                        <!--modified by-->
                        <div class="form-group">
                            <label class="col-xs-3">Người sửa</label>
                            <div class="col-xs-9">
                                <input type="text" name="modifiedBy" id="modifiedBy" class="form-control">
                            </div>
                        </div>

                        <!--manager name-->
                        <div class="form-group">
                            <label class="col-xs-3">Tên quản lý</label>
                            <div class="col-xs-9">
                                <input type="text" name="managerName" id="managerName" class="form-control">
                            </div>
                        </div>

                        <!--manager phone number-->
                        <div class="form-group">
                            <label class="col-xs-3">SĐT quản lý</label>
                            <div class="col-xs-9">
                                <input type="text" name="managerPhoneNumber" id="managerPhoneNumber"
                                       class="form-control">
                            </div>
                        </div>

                        <!--Điều khiển-->
                        <div class="form-group">
                            <label class="col-xs-3"></label>
                            <div class="col-xs-9">
                                <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">Thêm tòa nhà
                                </button>

                                <!--Cần điền tên URL chứ ko phải tên file-->
                                <a href="/admin/building-list-${item.id}">
                                    <!-- 1 cái button nằm trong form thì type mặc định của button sẽ theo type mặc định của form,
                                        nên phải đổi type của button để thực hiện chức năng khác-->
                                    <button type="button" class="btn btn-danger">Sửa tòa nhà</button>
                                </a>

                                <a href="/admin/building-list">
                                    <!-- 1 cái button nằm trong form thì type mặc định của button sẽ theo type mặc định của form,
                                        nên phải đổi type của button để thực hiện chức năng khác-->
                                    <button type="button" class="btn btn-danger">Hủy thao tác</button>
                                </a>
                            </div>
                        </div>

                    </form:form>
                </div>
            </div>
            <div class="hr hr-25 dotted hr-double"></div>

        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>

<script>
    // Hàm test dữ liệu
    $("#btnAddOrUpdateBuilding").click(function () {
        var formData = $("#form-edit").serializeArray(); // serializeArray(): Khởi tạo 1 mảng chứa các object-->
        var json = {};
        var typeCode = [];

        // function(i, v): i là chỉ số, v là kiểu dữ liệu của phần tử tại chỉ số đó
        $.each(formData, function (i, v) {
            // Nếu không lưu type code vào 1 mảng thì khi tích nhiều, sẽ chỉ lưu được 1 giá trị
            if (v.name == "typeCode") typeCode.push(v.value);
            else json["'" + v.name + "'"] = v.value;
        });

        json["'typeCode'"] = typeCode;

        btnAddOrUpdateBuilding(json);
        console.log(json);
    });

    // Hàm cho nút "Thêm tòa nhà"
    // Hàm gửi dữ liệu xuống cho server
    function btnAddOrUpdateBuilding(json) {
        $.ajax({
            // url: "http://localhost:8081/api/buildings"
            // Không cần thêm "http://localhost:8081" nữa vì mình đang xài server Tomcat thì nó cung cấp sẵn tên miền "http://localhost:8081" dồi
            url: "/api/buildings", // url đang sử dụng
            type: "POST", // HTTP method
            data: JSON.stringify(json), // , Phải thực hiện chuyển đổi kiểu dữ liệu của đối tượng "json" để gửi xuống server
            contentType: "application/json",  // Kiểu nội dung để gửi cho server
            dataType: "text",   // Kiểu dữ liệu để gửi cho client, bên Controller cũng phải trả ra đúng kiểu dữ liệu này
            success: function (result) {
                console.log("Thành công!");
                alert(result);
            },
            error: function (result) {
                console.log("Thất bại!");
                alert("Thêm mới thất bại!");
            }
        });
    }
</script>
</body>
</html>
