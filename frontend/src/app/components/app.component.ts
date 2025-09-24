import {Component} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LabSeqResponse, LabSeqService} from '../services/service';

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

  constructor(private labSeqService: LabSeqService) {}

  getLabSeq() {

    if (this.nValue < 0) {
      this.error = 'Must be a value equal ou greater to 0';
      return;
    }

    this.loading = true;
    this.error = '';
    this.result = '';

    this.labSeqService.getLabSeq(this.nValue).subscribe({
      next: (response: LabSeqResponse) => {
        this.result = response.value;
        this.loading = false;
      },

      error: (err) => {
        this.error = 'Error fetching data from server.';
        this.loading = false;
        console.error(err);
      },
    });
  }
}
