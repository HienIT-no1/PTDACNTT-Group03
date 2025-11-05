document.getElementById("registerForm").addEventListener("submit", function(e) {
    e.preventDefault();
    alert("Cảm ơn bạn đã đăng ký! Chúng tôi sẽ liên hệ sớm nhất.");
    this.reset();
});
