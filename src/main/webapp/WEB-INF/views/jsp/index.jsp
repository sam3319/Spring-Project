	<!--  -->
	
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
	        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
	        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
	        <!-- StyleSheet -->
	        <link rel="stylesheet" href="resources/css/index.css">
	    </head>
	    <body>
	        <!-- Container -->
	        <div id="container">
	            <!-- Header -->
	            <header>
	                <div class="header-box">
	                    <img src="resources/logoImg/logo2.jpg" width="50px">
	                    <span id="title">DDongstagram</span>
	                </div>
	                <i class="bi bi-send" id="header-btn"></i>
	            </header>
	            
	            <!-- Main -->
	            <main id="main-content">
	                <!-- 게시글들이 동적으로 추가될 영역 -->
	            </main>
	            
	            <!-- 관찰 대상 요소 (Sentinel) -->
	            <div id="sentinel" style="height: 1px;, display: none;"></div>
	            
	            <!-- Footer -->
	            <nav>
	                <i class="bi bi-house"></i>
	                <i class="bi bi-plus-square" onclick="location.href='/board/post'"></i>
	                <i class="bi bi-person" onclick="location.href='board/profile'"></i>
	            </nav>
	        </div>
	        
	        <!-- 게시글 상세 모달 -->
	        <div class="modal fade" id="boardDetailModal" tabindex="-1" aria-hidden="true">
	            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
	                <div class="modal-content">
	                    <!-- 모달 헤더 -->
	                    <div class="modal-header">
	                        <div id="reply-header">
	                            <div class="reply-box">
	                                <div class="profile-img" id="modalProfileImg"></div>
	                                <span class="user-name" id="modalUserName">DDong</span>
	                            </div>
	                            <i class="bi bi-three-dots-vertical profile-btn"></i>
	                        </div>
	                    </div>
	
	                    <!-- 모달 바디 -->
	                    <div class="modal-body">
	                        <article>
	                            <!-- 이미지 캐러셀 -->
	                            <div id="modalCarousel" class="carousel slide">
	                                <div class="carousel-inner reply-carousel" id="modalCarouselInner">
	                                    <div class="carousel-item active">
	                                        <img src="TestImage.png" class="d-block w-100">
	                                    </div>
	                                </div>
	                                <button class="carousel-control-prev" type="button" data-bs-target="#modalCarousel" data-bs-slide="prev">
	                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	                                    <span class="visually-hidden">Previous</span>
	                                </button>
	                                <button class="carousel-control-next" type="button" data-bs-target="#modalCarousel" data-bs-slide="next">
	                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	                                    <span class="visually-hidden">Next</span>
	                                </button>
	                            </div>
	                            
	                            <!-- 수정/삭제 버튼 -->
	                            <div id="content-nav">
	                                <div class="update" onclick="updateBoard()">수정</div>
	                                <div class="delete" onclick="deleteBoard()">삭제</div>
	                            </div>
	                        </article>
	                        
	                        <article id="reply-content">
	                            <!-- 게시글 내용 -->
	                            <div class="content-box">
	                                <span class="user-name" id="modalContentUserName">DDong</span>
	                                <span id="modalContent">게시글 내용</span>
	                            </div>
	                            
	                            <!-- 댓글 목록 -->
	                            <div class="reply-box">
	                                <div class="profile-img"></div>
	                                <span class="user-name">DDong</span>
	                                <span class="reply-comment"></span>
	                                <i class="bi bi-heart"></i>
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
	        
	        <!-- 부트스트랩 JS -->
	        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
	        
	        <!-- 무한스크롤 JS -->
	        <script src="${pageContext.request.contextPath}/resources/js/infinite-scroll.js"></script>
	    </body>
	</html>
