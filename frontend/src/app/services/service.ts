import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

export interface LabSeqResponse {
  n: number;
  value: string;
}

@Injectable({
  providedIn: 'root'
})
export class LabSeqService {
  private apiUrl = 'http://localhost:8080/labseq';

  constructor(private http: HttpClient) {}

  getLabSeq(n: number): Observable<LabSeqResponse> {
    return this.http.get<LabSeqResponse>(`${this.apiUrl}/${n}`);
  }
}


