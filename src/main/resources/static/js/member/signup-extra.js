document.addEventListener("DOMContentLoaded", () => {
  console.log("✅ JS loaded");

  const steps = document.querySelectorAll(".step");
  const nextBtn = document.querySelector(".next-btn");
  const submitBtn = document.querySelector(".submit-btn");
  let currentStep = 0;

  function goToNextStep() {
    const currentInput = steps[currentStep].querySelector("input");
    if (!currentInput.value.trim()) {
      alert("값을 입력해 주세요.");
      return;
    }

    currentStep++;

    if (currentStep < steps.length) {
      steps[currentStep].classList.remove("hidden");
      steps[currentStep].scrollIntoView({ behavior: "smooth", block: "center" });
      steps[currentStep].querySelector("input").focus();

      if (currentStep === steps.length - 1) {
        // 마지막 단계 진입 시: 다음 버튼 숨기고, 제출 버튼 표시
        nextBtn.classList.add("hidden");
        submitBtn.classList.remove("hidden");
      }
    }
  }

  nextBtn.addEventListener("click", goToNextStep);

  steps.forEach((step, index) => {
    const input = step.querySelector("input");
    input.addEventListener("keydown", (e) => {
      if (e.key === "Enter") {
        e.preventDefault();
        if (index === currentStep) {
          goToNextStep();
        }
      }
    });
  });
});
