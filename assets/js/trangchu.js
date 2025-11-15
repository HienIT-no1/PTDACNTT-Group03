// Clear search input field
function clearSearch() {
  document.getElementById("searchInput").value = "";
  document.getElementById("searchInput").focus();
}

// Handle form submission (hero section form)
// document.getElementById("registerForm").addEventListener("submit", function(e) { // {{change 1: Chuyển vào DOMContentLoaded}}
//   e.preventDefault();
//   alert("Cảm ơn bạn đã đăng ký! Chúng tôi sẽ liên hệ sớm nhất.");
//   this.reset();
// });

// Đảm bảo DOM đã sẵn sàng - tất cả logic chính nằm ở đây
document.addEventListener('DOMContentLoaded', () => {
  
  // Handle form submission (hero section form)
  document.getElementById("registerForm")?.addEventListener("submit", function(e) {
    e.preventDefault();
    alert("Cảm ơn bạn đã đăng ký! Chúng tôi sẽ liên hệ sớm nhất.");
    this.reset();
  });


// JavaScript for Modal Functionality

// Code cho modal
document.addEventListener('DOMContentLoaded', function() {
  const modalOverlay = document.getElementById('modal-overlay');
  const closeBtn = document.getElementById('modal-close-btn');

  if (!modalOverlay || !closeBtn) {
      console.error('Không tìm thấy modal. Check id trong HTML.');
      return;
  }

  // Hiện modal sau 5 giây
  setTimeout(function() {
      modalOverlay.classList.add('active');
      console.log('Modal đã hiện sau 5s'); // Log để debug
  }, 5000);

  // Đóng modal khi click nút close
  closeBtn.addEventListener('click', function() {
      modalOverlay.classList.remove('active');
      console.log('Modal đóng bằng nút');
  });

  // Đóng modal khi click ngoài nội dung
  modalOverlay.addEventListener('click', function(event) {
      if (event.target === modalOverlay) {
          modalOverlay.classList.remove('active');
          console.log('Modal đóng bằng click ngoài');
      }
  });
});

  
  // // ========== MODAL POPUP (5 seconds) ==========
  // setTimeout(() => {
  //   const modal = document.getElementById('modal-overlay');
  //   if (modal) {
  //     modal.classList.add('show');
  //   }
  // }, 5000);

  // // Close modal logic (giữ nguyên, đã ổn)
  // const closeBtn = document.getElementById('modal-close-btn');
  // const modalOverlay = document.getElementById('modal-overlay');
  
  // if (closeBtn) {
  //   closeBtn.addEventListener('click', () => {
  //     if (modalOverlay) {
  //       modalOverlay.classList.remove('show');
  //     }
  //   });
  // }

  // // Close modal when clicking outside (on overlay)
  // if (modalOverlay) {
  //   modalOverlay.addEventListener('click', (e) => {
  //     if (e.target === modalOverlay) {
  //       modalOverlay.classList.remove('show');
  //     }
  //   });
  // }

  // Handle modal form submission (giữ nguyên, đã ổn)
  const modalForm = document.getElementById('modalRegisterForm');
  if (modalForm) {
    modalForm.addEventListener('submit', (e) => {
      e.preventDefault();
      alert("Cảm ơn bạn đã đăng ký! Chúng tôi sẽ liên hệ sớm nhất.");
      modalForm.reset();
      if (modalOverlay) {
        modalOverlay.classList.remove('show');
      }
    });
  }

// ===================================================
// ========== TIKTOK VIDEO (SỬ DỤNG API MỚI) ==========
// ===================================================

document.addEventListener("DOMContentLoaded", () => {
  const container = document.getElementById("tiktok-player");

  if (!container) return;

  container.innerHTML = '<div class="loading">Đang tải video...</div>';

  const videoUrl = "https://www.tiktok.com/@taochamhoi/video/7570620725875248402";
  const api = "https://www.tikwm.com/api/?url=" + encodeURIComponent(videoUrl);

  fetch(api)
      .then(res => res.json())
      .then(data => {
          const mp4 = data?.data?.play;
          if (!mp4) {
              container.innerHTML = "<div class='error'>Không tải được video!</div>";
              return;
          }

          // Xóa loading
          container.innerHTML = "";

          // Tạo video
          const video = document.createElement("video");
          video.src = mp4;
          video.autoplay = true;
          video.loop = true;
          video.muted = true;
          video.playsInline = true;
          video.style.width = "100%";
          video.style.borderRadius = "10px";

          // Tạo nút
          const btn = document.createElement("button");
          btn.className = "tiktok-btn";
          btn.innerHTML = "❚❚"; // đang chạy → hiện nút pause

          // Style nút (bạn có thể CSS lại trong file của bạn)
          btn.style.position = "absolute";
          btn.style.bottom = "10px";
          btn.style.right = "10px";
          btn.style.padding = "5px 10px";
          btn.style.borderRadius = "6px";
          btn.style.border = "none";
          btn.style.background = "rgba(0,0,0,0.6)";
          btn.style.color = "white";
          btn.style.cursor = "pointer";

          // Cho container position để button nằm đúng
          container.style.position = "relative";

          container.appendChild(video);
          container.appendChild(btn);

          // Sự kiện toggle play/pause
          btn.addEventListener("click", () => {
              if (video.paused) {
                  video.play();
                  btn.innerHTML = "❚❚";  // pause icon
              } else {
                  video.pause();
                  btn.innerHTML = "▶";   // play icon
              }
          });
      })
      .catch(err => {
          container.innerHTML = "<div class='error'>Lỗi tải video</div>";
          console.error(err);
      });
});
// =======
  
  // ========== SMOOTH SCROLL LOGO (giữ nguyên, đã ổn) ==========
  const logo = document.querySelector('.logo');
  if (logo) {
    logo.addEventListener('click', (e) => {
      e.preventDefault();
      const duration = 1500;
      const start = window.scrollY;
      const startTime = performance.now();

      function smoothScroll(currentTime) {
        const elapsed = currentTime - startTime;
        const progress = Math.min(elapsed / duration, 1);
        
        // Công thức cuộn mượt (dùng ease-out đơn giản)
        // window.scrollTo(0, start * (1 - progress)); // Cuộn về 0
        
        // Điều chỉnh: Dùng easing function (ví dụ: ease-out-quad)
        const easing = progress * (2 - progress);
        window.scrollTo(0, start * (1 - easing));

        if (progress < 1) {
          requestAnimationFrame(smoothScroll);
        }
      }
      requestAnimationFrame(smoothScroll);
    });
  };
});