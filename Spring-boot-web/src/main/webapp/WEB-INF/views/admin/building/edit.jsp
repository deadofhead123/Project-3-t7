<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
                    <form:form method="get" id="form-edit" modelAttribute="buildingEdit">
                        <input type="hidden" name="id" value="${buildingEdit.id}"/>
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
                                <form:input path="name" class="form-control" placeholder="Gõ tên tòa nhà..."/>
                            </div>
                        </div>

                        <!--district id-->
                        <div class="form-group">
                            <label class="col-xs-3">Quận</label>
                            <div class="col-xs-3">
                                <form:select path="district"
                                             class="form-control"> <!--Cái path này liên quan đến bên BuildingSearchRequest-->
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
<%--                                <input type="number" name="numberOfBasement" id="numberOfBasement"--%>
<%--                                       class="form-control">--%>
                                <form:input path="numberOfBasement" class="form-control"/>
                            </div>
                        </div>

                        <!--floor area-->
                        <div class="form-group">
                            <label class="col-xs-3">Diện tích sàn</label>
                            <div class="col-xs-9">
<%--                                <input type="number" name="floorArea" id="floorArea" class="form-control">--%>
                                <form:input path="floorArea" class="form-control"/>
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

                        <!--rent area-->
                        <div class="form-group">
                            <label class="col-xs-3">Diện tích thuê</label>
                            <div class="col-xs-9">
                                    <%--                                <input type="number" name="rentPrice" id="rentPrice" class="form-control">--%>
                                <form:input path="rentArea" class="form-control"/>
                            </div>
                        </div>

                        <!--rent price-->
                        <div class="form-group">
                            <label class="col-xs-3">Giá thuê</label>
                            <div class="col-xs-9">
<%--                                <input type="number" name="rentPrice" id="rentPrice" class="form-control">--%>
                                <form:input path="rentPrice" class="form-control"/>
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

                        <!--manager name-->
                        <div class="form-group">
                            <label class="col-xs-3">Tên quản lý</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="managerName" id="managerName" class="form-control">--%>
                                <form:input path="managerName" class="form-control"/>
                            </div>
                        </div>

                        <!--manager phone number-->
                        <div class="form-group">
                            <label class="col-xs-3">SĐT quản lý</label>
                            <div class="col-xs-9">
<%--                                <input type="text" name="managerPhoneNumber" id="managerPhoneNumber"--%>
<%--                                       class="form-control">--%>
                                <form:input path="managerPhoneNumber" class="form-control"/>
                            </div>
                        </div>

                        <!--Điều khiển-->
                        <div class="form-group">
                            <label class="col-xs-3"></label>
                            <div class="col-xs-9">
                                <!-- - Vì cái thêm và sửa tòa nhà dùng chung form, nên trong cái form đó mà cả nút thêm tòa nhà và sửa hiện ra thì ko hợp lí lắm.
                                     - Mong muốn là: ấn sửa thì trong form chỉ hiện ra nút sửa, ấn thêm thì chỉ hiện ra nút thêm
                                     - Nhận thấy:
                                       + Khi thêm thì ko gửi id xuống (vì id sẽ tự sinh sau khi tòa nhà được tạo mới)
                                       + Khi thêm thì gửi id xuống
                                       -> Ta dựa vào đó xây dựng điều kiện hiển thị nút Thêm và nút Sửa -->
                                <c:if test="${empty buildingEdit.id}">
                                    <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">Thêm tòa nhà</button>
                                </c:if>

                                <c:if test="${not empty buildingEdit.id}">
                                    <button type="button" class="btn btn-warning" id="btnAddOrUpdateBuilding">Sửa thông tin</button>
                                </c:if>

                                <button type="reset" class="btn btn-default">Xóa thông tin</button>

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
            else if(v.name == "buildingDTOs") v.value = [];
            else json["" + v.name + ""] = v.value; // json["'" + v.name + "'"] = v.value; là sai, còn sai như thế nào thì nháp ra giấy là hiểu
        });

        json['typeCode'] = typeCode;

        console.log(json);

        if(confirm("Xác nhận các thông tin chính xác?")){
            addOrUpdateBuilding(json);
        }
    });

    // Hàm cho nút "Thêm tòa nhà"
    // Hàm gửi dữ liệu xuống cho server
    function addOrUpdateBuilding(json) {
        $.ajax({
            url: "/api/buildings",
            type: "POST",
            data: JSON.stringify(json),
            contentType: 'application/json',
            dataType: 'JSON',
            success: function(result){
                console.log(result);
                alert(result.message);
            },
            error: function(result){
                console.log(result);

                var row = '';

                $.each(result.responseJSON.details, function(index, item){
                   row += item + "\n";
                });

                alert(row);
            }
        });
    }
</script>
</body>
</html>
