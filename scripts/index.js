// первым блоком script для модального окна и формы отправки

// для строки поиска

// для отрисовки карточек и их анимации

const cardList = document.querySelector('.cards');
const demo = document.querySelector('.demo');
const loader = document.querySelector('.loader');

window.setTimeout(() => {
  autoComplete();

}, 3000);

// const modal = document.querySelector('#modal');
// const close = document.querySelector('.modal__card-close');

// close.onclick = function () {
//   modal.style.display = 'none';
// };

function autoComplete () {
  fetch ("http://localhost:8080/cards")

  .then((response) => {
    return response.json();
  })

  .then((data) => {
    // const namesList = data.map(item => item.name);

    let card = "";

    for (let i = 0; i <= data.length; i++) {

      const item = new Object(data[i]);
      card += ` <label>
        <input type="checkbox"  />
        <div class="card">
          <div class="front">
            <p class="card-titly">${item['name']}</p>
          </div>
          <div class="back">
            <p class="card-titly">${item['name']}</p>
            <p class="card-text">${item['description']}</p>
          </div>
        </div>
      </label>`
    };

    cardList.innerHTML = card;
  })

  .catch ((e) => {
    console.error(e);
    demo.classList.remove("none");
  })

  .finally(() => {
    loader.classList.add("none");
  });
};


const btnUp = {
  el: document.querySelector('.btn-up'),
  scrolling: false,
  show() {
    if (this.el.classList.contains('btn-up_hide') && !this.el.classList.contains('btn-up_hiding')) {
      this.el.classList.remove('btn-up_hide');
      this.el.classList.add('btn-up_hiding');
      window.setTimeout(() => {
        this.el.classList.remove('btn-up_hiding');
      }, 300);
    }
  },
  hide() {
    if (!this.el.classList.contains('btn-up_hide') && !this.el.classList.contains('btn-up_hiding')) {
      this.el.classList.add('btn-up_hiding');
      window.setTimeout(() => {
        this.el.classList.add('btn-up_hide');
        this.el.classList.remove('btn-up_hiding');
      }, 300);
    }
  },
  addEventListener() {
    // при прокрутке окна (window)
    window.addEventListener('scroll', () => {
      const scrollY = window.scrollY || document.documentElement.scrollTop;
      if (this.scrolling && scrollY > 0) {
        return;
      }
      this.scrolling = false;
      // если пользователь прокрутил страницу более чем на 200px
      if (scrollY > 400) {
        // сделаем кнопку .btn-up видимой
        this.show();
      } else {
        // иначе скроем кнопку .btn-up
        this.hide();
      }
    });
    // при нажатии на кнопку .btn-up
    document.querySelector('.btn-up').onclick = () => {
      this.scrolling = true;
      this.hide();
      // переместиться в верхнюю часть страницы
      window.scrollTo({
        top: 0,
        left: 0,
        behavior: 'smooth'
      });
    }
  }
}

btnUp.addEventListener();