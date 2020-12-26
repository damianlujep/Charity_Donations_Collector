document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // TODO: get data from inputs and show them in summary
      let categories, bagsNumber, institution, street, city, zipCode, phone, pickUpDate, pickUpTime, pickUpComment;
      categories = document.querySelectorAll("input[type='checkbox']:checked");

      let categoriesNames = [];
      for (let c of categories){
        categoriesNames.push(c.parentNode.lastElementChild.textContent);
        console.log(categoriesNames)
      }

      bagsNumber = document.querySelector("input[type='number']");
      institution = document.querySelector("input[type='radio']:checked");
      let institutionName = institution.parentNode.lastElementChild.firstElementChild.textContent;
      console.log(institutionName)

      street = document.getElementById("street");
      city = document.getElementById("city");
      zipCode = document.getElementById("zipCode");
      phone = document.querySelector("input[type='tel']");
      pickUpDate = document.querySelector("input[type='date']");
      pickUpTime = document.querySelector("input[type='time']");
      pickUpComment = document.querySelector("textarea");

      let bagsNumberAndCategorySumm, institutionSumm, listWithAddressAndPhone, listWithPickUpInfo;
      bagsNumberAndCategorySumm = document.getElementById("bagsNumberAndCategorySumm");
      institutionSumm = document.getElementById("institutionSumm");
      listWithAddressAndPhone = document.getElementById("listWithAddressAndPhone");
      listWithPickUpInfo = document.getElementById("listWithPickUpInfo");

      bagsNumberAndCategorySumm.innerText = bagsNumber.value + " worki z kategorii: " + categoriesNames.join(", ");
      institutionSumm.innerText = "Dla fundacji: " + institutionName;

      listWithAddressAndPhone.innerText = "";

      let li1 = document.createElement("li");
      li1.innerText = street.value;
      listWithAddressAndPhone.appendChild(li1);

      let li2 = document.createElement("li");
      li2.innerText = city.value;
      listWithAddressAndPhone.appendChild(li2);

      let li3 = document.createElement("li");
      li3.innerText = zipCode.value;
      listWithAddressAndPhone.appendChild(li3);

      let li4 = document.createElement("li");
      li4.innerText = phone.value;
      listWithAddressAndPhone.appendChild(li4);

      listWithPickUpInfo.innerText = "";

      let li5 = document.createElement("li");
      li5.innerText = pickUpDate.value;
      listWithPickUpInfo.appendChild(li5);

      let li6 = document.createElement("li");
      li6.innerText = pickUpTime.value;
      listWithPickUpInfo.appendChild(li6);

      let li7 = document.createElement("li");
        if (pickUpComment.value === ""){
          li7.innerText = "Brak uwagi";
        } else {
          li7.innerText = pickUpComment.value;
        }
        listWithPickUpInfo.appendChild(li7);
    }

  }
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
});
