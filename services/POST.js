export class POST {
    static async postCard(data) {
      try {
        await fetch("http://localhost:8080/cards", {
          method: "POST",
          headers: { "Content-Type": "application/json;charset=utf-8" },
          body: JSON.stringify(data),
        });
      } catch (e) {
        console.error(e);
      }
    }
  }
  export default POST;