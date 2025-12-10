<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/post.css">
    </head>
    <body>
        <!-- 부트스트랩 -->
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
            crossorigin="anonymous"></script>
        <main>
            <!-- 입력 폼 -->
            <form action="${pageContext.request.contextPath}/board/update" method="post" enctype="multipart/form-data" id="updateForm">
            
            	<input type="hidden" name="bno" value="${board.bno}">
                <header>
                <div class="header-box">
                    <span id="title">게시글 작성</span>
                </div>
            </header>
                <!-- 제목 -->
                <section>
                    <span>제목</span><input type="text" name="title" id="title"
                        required value="${board.title }">
                </section>

                <!-- 내용 -->
                <section>
                    <span>내용</span><textarea name="content"
                        id="content" required>${board.content }</textarea>
                </section>

                <!-- 파일 -->
                <span>사진첨부</span>
                <section>
                    <div class="file-container">
                        <label for="file">
                            <i class="bi bi-plus"></i>이미지 수정 불가
                        </label>
                    </div>
                    <input type="file" name="files" id="file" placeholder="파일 선택" disabled>
                </section>

                <!-- 폼 버튼 -->
                <section id="form-btn">
                    <input type="submit" value="수정">
                    <input type="button" value="취소"
                        onclick="location.href='/'">
                </section>
            </form>
            <nav>
                <i class="bi bi-house" onclick="location.href='/'"></i>
                <i class="bi bi-plus-square"></i>
                <i class="bi bi-person" onclick="location.href='/board/profile'"></i>
            </nav>
        </main>
    </body>
</html>