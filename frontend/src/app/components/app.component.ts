import {Component} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LabSeqResponse, LabSeqService} from '../services/service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-labseq',
  templateUrl: './app.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule],
})
export class AppComponent {
  nValue: number = 0;
  result : string = '';
  loading: boolean = false;
  error: string = '';

  constructor(private labSeqService: LabSeqService, private http: HttpClient) {}

  getLabSeq() {

    if (this.nValue < 0) {
      this.error = 'Must be a value equal ou greater to 0';
      return;
    }

    this.loading = true;
    this.error = '';
    this.result = '';


    this.http.get(`http://localhost:8080/labseq/${this.nValue}`, { responseType: 'text'}).subscribe({
      next: (response: string) => {
        this.result = response;
        console.log('LabSeq result:', response);
        this.loading = false;

        if(response){
          console.log('✅ Object keys:', Object.keys(response));
          console.log('✅ Full object:', JSON.stringify(response, null, 2));
        }
      },

      error: (err) => {
        this.error = 'Error fetching data from server.';
        this.loading = false;
        console.error(err);
      },
    });
  }
}
