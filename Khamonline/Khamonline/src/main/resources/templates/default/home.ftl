<#include "/default/inc/layout.ftl"/>
<@layout>
    <div class="row">
        <h1 class="introduce-title">Giới thiệu về phòng khám online</h1>
    </div>
    <div class="row">
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner">
                <div class="item active">
                    <img src="${base}/dist/images/bs2.png" alt="Los Angeles" style="width:100%;">
                </div>

                <div class="item">
                    <img src="${base}/dist/images/image-bs-one.png" alt="Los Angeles" style="width:100%;">
                </div>

                <div class="item">
                    <img src="${base}/dist/images/bs3.png" alt="Los Angeles" style="width:100%;">
                </div>
            </div>

            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
    <div class="row middle-page">
        <img src="${base}/dist/images/bs4.png" alt="Los Angeles" style="width:100%;">
    </div>
    <div class="row">
        <h3 class="introduce-title">Đội ngũ chuyên gia</h3>
    </div>
    <div class="row bs-footer">
        <div id="myCarousel1" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel1" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel1" data-slide-to="1"></li>
                <li data-target="#myCarousel1" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner">

                <div class="item active">
                    <img src="${base}/dist/images/bs-lam1.png" alt="Los Angeles" style="width:100%;">
                    <div class="carousel-caption">
                        <h3>Bác sỹ Bùi Ngọc Lâm</h3>
                        <p>Chuyên khoa tiết niệu</p>
                    </div>
                </div>

                <div class="item">
                    <img src="${base}/dist/images/bs-loan1.png" alt="Los Angeles" style="width:100%;">
                    <div class="carousel-caption">
                        <h3>Bs. Nguyễn Phương Loan</h3>
                        <p>Chuyên khoa phụ sản</p>
                    </div>
                </div>

                <div class="item">
                    <img src="${base}/dist/images/bs-trinh.png" alt="Bs Trịnh" style="width:100%;">
                    <div class="carousel-caption">
                        <h3>Bs. Nguyễn Đường Trịnh</h3>
                        <p>Chuyên khoa tim mạch</p>
                    </div>
                </div>

            </div>

            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel1" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel1" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
</@layout>
