export class GET {
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
  export default GET;