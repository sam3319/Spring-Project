let currentPage = 1;
let pageSize = 5;
let isLoading = false;
let hasMore = true;
let observer = null;

let currentBno;
// 페이지 로드 시 실행
document.addEventListener("DOMContentLoaded", function(){
    // 첫 페이지 로드
    loadBoardList();
    
    // IntersectionObserver 설정
    setupIntersectionObserver();
});

function setupIntersectionObserver(){
    const sentinel = document.getElementById('sentinel');

    // Observer 설정
    const options = {
        root: null,
        rootMargin: '0px 0px 100px 0px',
        threshold: 0.1
    };
    
    // Observer 콜백함수
    const callback = (entries, obs) => {
        entries.forEach(entry => {
            // sentinel이 화면 상에 보일 경우
            if(entry.isIntersecting && !isLoading && hasMore){
                currentPage++;
                loadBoardList();
            }
        });
    };

    // IntersectionObserver 인스턴스 생성
    observer = new IntersectionObserver(callback, options);

    // sentinel 객체 관찰 시작
    observer.observe(sentinel);
}

async function loadBoardList(){
    if(isLoading) return;

    isLoading = true;

    try{
        console.log(`API 호출: page=${currentPage}, size=${pageSize}`);
        
        const response = await fetch(`/api/board/list?page=${currentPage}&size=${pageSize}`);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const boards = await response.json();
        
        console.log('받은 게시글:', boards);

        // 더 이상 서버에서 받아올 JSON 데이터가 없을 경우
        if(boards.length === 0){
            hasMore = false;
            
            // Observer 중지
            if(observer){
                observer.disconnect();
            }
            
            // 첫 페이지인데 데이터가 없으면 메시지 표시
            if(currentPage === 1) {
                document.getElementById('main-content').innerHTML = 
                    '<p style="text-align:center; padding:40px; color:#888;">게시글이 없습니다.</p>';
            }
            return;
        }
        
        // 각 게시글 추가
        boards.forEach((board) => {
            appendBoardItem(board);
        });

        // 데이터가 pageSize보다 적으면 마지막 페이지
        if(boards.length < pageSize){
            hasMore = false;
            if(observer){
                observer.disconnect();
            }
        }
    }catch(error){
        console.error('게시글 로드 에러:', error);
        alert('게시글을 불러오는데 실패했습니다.');
    }finally{
        isLoading = false;
    }
}

// 게시글 HTML 추가
function appendBoardItem(board){
    const mainContent = document.getElementById('main-content');

    const section = document.createElement('section');
    section.className = 'board-section';
    section.setAttribute('data-bno', board.bno);

    const images = board.images || [];

    section.innerHTML = `
        <!-- 프로필 -->
        <article class="profile">
            <div class="profile-box">
                <div class="profile-img" 
				     style="background-image: url('/resources/userImg/profile.png'); 
				            background-size: cover; 
				            background-position: center; 
				            width: 25px;
				            height: 25px; 
				            border-radius: 50%;"></div>
                <span class="user-name">${escapeHtml(board.nickName)}</span>
            </div>
            <i class="bi bi-three-dots-vertical profile-btn"></i>
        </article>
        
        ${createCarousel(board)}
        
        <!-- 게시글 버튼 -->
        <article class="content-btn">
            <div>
                <i class="bi bi-heart"></i>
                <i class="bi bi-chat" onclick="openBoardDetail(${board.bno})"></i>
                <i class="bi bi-send"></i>
            </div>
            <i class="bi bi-bookmark"></i>
        </article>
        
        <!-- 게시글 정보 -->
        <article class="content-info">
            <span class="user-name">${escapeHtml(board.nickName)}</span>
            <span class="content-body">${escapeHtml(board.content || '')}</span>
        </article>
        
        <!-- 댓글 버튼 -->
        <button type="button" class="replay-btn" onclick="openBoardDetail(${board.bno})">
            댓글 ${board.replyCount || 0}개 모두 보기
        </button>
        
        <!-- 작성 시간 -->
        <article class="content-date">
            <span>${formatDate(board.createAt)}</span>
        </article>
    `;

    mainContent.appendChild(section);
}


// 캐러셀 생성
function createCarousel(board){
    const images = board.images || [];

    if(images.length === 0){
        return `<div style="text-align:center; padding:80px 40px; background:#f5f5f5; color:#888;">
                    <i class="bi bi-image" style="font-size:40px;"></i><br>
                    이미지가 없습니다
                </div>`;
    }

    const carouselId = `carousel-${board.bno}`;

    return `
        <div id="${carouselId}" class="carousel slide" style="width: 100%;">
            ${images.length > 1 ? `
            <div class="carousel-indicators">
                ${images.map((img, index) => `
                    <button type="button" 
                            data-bs-target="#${carouselId}" 
                            data-bs-slide-to="${index}" 
                            ${index === 0 ? 'class="active"' : ''}></button>
                `).join('')}
            </div>
            ` : ''}
            
            <div class="carousel-inner">
                ${images.map((img, index) => `
                    <div class="carousel-item ${index === 0 ? 'active' : ''}">
                        <img src="/upload/boards/${img}" alt="게시글 이미지">
                    </div>
                `).join('')}
            </div>
            
            ${images.length > 1 ? `
                <button class="carousel-control-prev" type="button" data-bs-target="#${carouselId}" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#${carouselId}" data-bs-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </button>
            ` : ''}
        </div>
    `;
}



// 서버에서 받아온 문자열 객체를 HTML 형태로 치환하는 함수
function escapeHtml(text) {
    if (!text) return '';
    const div = document.createElement('div');  
    div.textContent = text;
    return div.innerHTML;
}

// 날짜 포맷 함수
function formatDate(dateString) {
    if (!dateString) return '';
    
    const date = new Date(dateString);
    const now = new Date();
    const diff = Math.floor((now - date) / 1000); // 초 단위
    
    if (diff < 60) return '방금 전';
    if (diff < 3600) return `${Math.floor(diff / 60)}분 전`;
    if (diff < 86400) return `${Math.floor(diff / 3600)}시간 전`;
    if (diff < 604800) return `${Math.floor(diff / 86400)}일 전`;
    
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    
    return `${year}년 ${month}월 ${day}일`;
}

// 게시글 상세 페이지로 이동
function openBoardDetail(bno) {
    location.href = `/board/get?bno=${bno}`;
}

let currentBoardBno = null;
let boardDetailModal = null;

// 페이지 로드 시 모달 초기화
document.addEventListener("DOMContentLoaded", function(){
    console.log('=== 페이지 로드됨 ===');
    
    // 모달 객체 초기화
    const modalElement = document.getElementById('boardDetailModal');
    if(modalElement) {
        boardDetailModal = new bootstrap.Modal(modalElement);
    }
    
    currentPage = 1;
    loadBoardList();
    
    setTimeout(() => {
        setupIntersectionObserver();
    }, 500);
});

// 게시글 상세 보기
async function openBoardDetail(bno) {
    console.log('게시글 상세 보기:', bno);
    
    try {
        const response = await fetch(`/api/board/${bno}`);
        if (!response.ok) {
            throw new Error('게시글을 불러올 수 없습니다.');
        }
        
        const board = await response.json();
        console.log('상세 게시글:', board);
        
        currentEditBno = board.bno;
        
		// 프로필 이미지 
		const profileImg = document.getElementById('modalProfileImg');
		if(profileImg) {
		    profileImg.style.backgroundImage = `url('/resources/userImg/profile.png')`;
		    profileImg.style.backgroundSize = 'cover';
		    profileImg.style.backgroundPosition = 'center';
		}
		        
        // 사용자 이름
        const modalUserName = document.getElementById('modalUserName');
        const modalContentUserName = document.getElementById('modalContentUserName');
        if(modalUserName) modalUserName.textContent = board.nickName;
        if(modalContentUserName) modalContentUserName.textContent = board.nickName;
        
        // 이미지 캐러셀
        const carouselInner = document.getElementById('modalCarouselInner');
		if(carouselInner) {
		    if(board.images && board.images.length > 0) {
		        carouselInner.innerHTML = board.images.map((img, index) => `
		            <div class="carousel-item ${index === 0 ? 'active' : ''}">
		                <img src="/upload/boards/${img}" 
		                     alt="게시글 이미지">
		            </div>
		        `).join('');
		    } else {
		        carouselInner.innerHTML = `
		            <div class="carousel-item active">
		                <div style="padding:100px; text-align:center; background:#f5f5f5; color:#888;">
		                    <i class="bi bi-image" style="font-size:40px;"></i><br>이미지가 없습니다
		                </div>
		            </div>`;
		    }
		}
        
        // 게시글 내용
        const modalContent = document.getElementById('modalContent');
        if(modalContent) {
            modalContent.textContent = board.content || '';
        }
        
        // 날짜
        const modalDate = document.getElementById('modalDate');
        if(modalDate) {
            modalDate.textContent = formatDate(board.createAt);
        }
        
        // 모달 열기
        if(boardDetailModal) {
            boardDetailModal.show();
        }
        
    } catch(error) {
        console.error('에러:', error);
        alert('게시글을 불러오는데 실패했습니다.');
    }
}

// 게시글 삭제 함수
function deleteBoard() {
    if(!confirm("정말 삭제하시겠습니까?")) {
        return;
    }
    
    if(!currentEditBno) {
        alert('게시글 정보를 찾을 수 없습니다.');
        return;
    }
    
    const form = document.createElement('form');
    form.method = 'POST';
    form.action = '/board/delete';
    
    const input = document.createElement('input');
    input.type = 'hidden';
    input.name = 'bno';
    input.value = currentEditBno;  // ✅ 전역 변수 사용
    
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
}

// 게시글 수정 함수
function updateBoard() {
    if(!currentEditBno) {
        alert('게시글 정보를 찾을 수 없습니다.');
        return;
    }
    
    location.href = `/board/update?bno=${currentEditBno}`;
}