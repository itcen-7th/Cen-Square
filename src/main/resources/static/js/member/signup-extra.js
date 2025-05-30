document.addEventListener("DOMContentLoaded", () => {
  initStepUI();
  initFormSubmit();
});

// 단계별 동적 UI
function initStepUI() {
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
}

// 서버로 데이터 전송 로직
function initFormSubmit() {
  const form = document.querySelector("#extraForm");

  form.addEventListener("submit", async function (e) {
    e.preventDefault();

    const batchVal = document.querySelector("#batchInput").value;
    const payload = {
      batchNumber: batchVal === "0" ? null : Number(batchVal),
      nickname: document.querySelector("#nickname").value,
      name: document.querySelector("#name").value
    };

    try {
      await axios.post("/api/v1/member/signup-extra", payload, {
        headers: {
          "Content-Type": "application/json"
        }
      });

      alert("저장 완료!");
      window.location.href = "/";
    } catch (error) {
      console.error("전송 실패:", error);
      alert("저장에 실패했습니다. 다시 시도해주세요.");
    }
  });
}
