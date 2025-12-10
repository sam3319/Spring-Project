<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>DDongstagram</title>
        <!-- 부트스트랩 -->
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
            crossorigin="anonymous">
        <link rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
        <!-- StyleSheet -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/profile.css">
    </head>
    <body>
        <!-- 부트스트랩 -->
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
            crossorigin="anonymous"></script>

        <!-- Container -->
        <div id="container">
            <!-- Header -->
            <header>
                <div class="header-box">
                    <span id="title">${loginUser.nickName }</span>
                </div>
                <i class="bi bi-list" id="header-btn"></i>
            </header>
            <!-- Main -->
            <main>
                <!-- Section -->
                <section id="profile-container">
                    <article>
                        <figure></figure>
                        <span>${loginUser.nickName }</span>
                    </article>
                    <!-- Profile -->
                    <article>
                        <table>
                            <tr class="profile-score">
                                <th>${postCount}</th>
                                <th>250</th>
                                <th>120</th>
                            </tr>
                            <tr class="profile-text">
                                <td>Posts</td>
                                <td>Followers</td>
                                <td>Following</td>
                            </tr>
                        </table>
                    </article>
                </section>

                <!-- 회원정보 수정 -->
                <section>
                    <div id="edit">Edit Profile</div>
                </section>

                <!-- Profile Navigation -->
                <nav id="profile-nav">
                    <div><i class="bi bi-grid-3x3"></i></div>
                    <div><i class="bi bi-person-square"></i></div>
                </nav>

                <div class="text-center">
				    <div class="row">
				        <c:forEach var="post" items="${thumbnails}" varStatus="status">
				            <c:if test="${status.index % 3 == 0 && !status.first}">
				                </div><div class="row"> <!-- 다음 줄 -->
				            </c:if>
				            <div class="col"
				                 style="cursor:pointer; padding:2px;"
				                 onclick="openBoardDetail(${post.bno})">
				                <c:if test="${post.thumbnail != null}">
				                    <img src="${pageContext.request.contextPath}/upload/boards/${post.thumbnail}"
				                         style="width:100%; aspect-ratio:1/1; object-fit:cover;">
				                </c:if>
				            </div>
				        </c:forEach>
				    </div>
				</div>

            </main>

            <!-- Footer -->
            <nav>
                <i class="bi bi-house" onclick="location.href='/'"></i>
                <i class="bi bi-plus-square"
                    onclick="location.href='/board/post'"></i>
                <i class="bi bi-person"></i>
            </nav>

        </div>


        <!-- 댓글 모달 창 -->
                    <div class="modal fade" id="exampleModal" tabindex="-1"
                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                            <div class="modal-content">

                                <!-- 댓글 헤더 -->
                                <div class="modal-header">
                                    <article id="reply-header">
                                        <div class="reply-box">
                                            <div id="profile-img"></div>
                                            <span class="user-name">DDong</span>
                                        </div>
                                        <i class="bi bi-three-dots-vertical" id="profile-btn"></i>
                                    </article>
                                </div>

                                <!-- 댓글 바디 -->
                                <div class="modal-body">
                                    <article>
                                        <div id="carouselExample" class="carousel slide">
                                            <div class="carousel-inner reply-carousel">
                                                <div class="carousel-item active">
                                                    <img src="TestImage.png" class="d-block w-100">
                                                </div>
                                                <div class="carousel-item">
                                                    <img src="TestImage.png" class="d-block w-100">
                                                </div>
                                                <div class="carousel-item">
                                                    <img src="TestImage.png" class="d-block w-100">
                                                </div>
                                            </div>
                                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
                                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                            <span class="visually-hidden">Previous</span>
                                            </button>
                                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
                                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                            <span class="visually-hidden">Next</span>
                                            </button>
                                            </div>
                                            <div id="content-nav">
                                                <div class="update" onclick="location.href='update.html' ">수정</div>
                                                <div class="delete">삭제</div>
                                            </div>
                                    </article>
                                    <article id="reply-content">
                                        <div class="content-box">
                                            게시글 내용
                                        </div>
                                        <div class="reply-box">
                                            <div id="profile-img"></div>
                                            <span class="user-name">DDong</span>
                                            <span class="reply-comment">댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용</span>
                                            <i class="bi bi-heart"></i></i>
                                        </div>
                                    </article>

                                </div>
                                <!-- 댓글 입력 폼 -->
                                <form action="" method="post" name="reply" id="reply-form">
                                    <div class="reply-form-container">
                                        <input type="text" placeholder="댓글 달기...">
                                        <button type="submit">게시</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
		<script src="${pageContext.request.contextPath}/resources/js/infinite-scroll.js"></script>
    </body>
</html>