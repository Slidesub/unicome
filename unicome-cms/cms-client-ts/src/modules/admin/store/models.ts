export class ApiCall {
    public status: number;
    public statusText: string;
    public message: string;
    constructor(call: any = {}) {
      this.status = call.status;
      this.statusText = call.statusText;
      this.message = call.message;
    }
  }