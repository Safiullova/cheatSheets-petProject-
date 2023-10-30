// первым блоком script для модального окна и формы отправки

// для строки поиска

// для отрисовки карточек и их анимации

class GET {
    static async getCards() {
      try {
        const resp = await fetch("http://localhost:8080/cards");
        const data = await resp.json();
        console.log(data);
        return data;
      } catch (e) {
        console.error(e);
      }
    }
  }

  const result = GET.getCards();
  console.log(result)