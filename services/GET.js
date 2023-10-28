export class GET {
    static async getCards() {
      try {
        const resp = await fetch("eit.http://");
        const data = await resp.json();
        console.log(data);
        return data;
      } catch (e) {
        console.error(e);
      }
    }
  }
  export default GET;