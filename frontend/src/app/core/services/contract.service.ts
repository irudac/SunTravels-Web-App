import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Contract, SearchRequest, SearchResponse } from '../../models/contract.model';

@Injectable({
  providedIn: 'root'
})
export class ContractService {
  private baseUrl = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient) {}

  getAllContracts(): Observable<Contract[]> {
    return this.http.get<Contract[]>(`${this.baseUrl}/contract/all`);
  }

  getContract(id: number): Observable<Contract> {
    const params = new HttpParams().set('id', id.toString());
    return this.http.get<Contract>(`${this.baseUrl}/contract`, { params });
  }  

  createContract(contractData: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/contract`, contractData);
  }  

  searchContracts(searchRequest: SearchRequest): Observable<SearchResponse[]> {
    return this.http.post<SearchResponse[]>(`${this.baseUrl}/contract/search`, searchRequest);
  }

  deleteContract(id: number): Observable<string> {
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete<string>(`${this.baseUrl}/contract`, { params });
  }

  updateContract(id: number, contractData: any): Observable<any> {
    const params = new HttpParams().set('id', id.toString());
    return this.http.put<any>(`${this.baseUrl}/contract`, contractData, { params });
  }
  
}
