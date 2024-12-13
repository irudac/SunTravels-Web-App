import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContractSearchComponent } from './contract-search/contract-search.component';
import { ContractResultsComponent } from './contract-results/contract-results.component';

const routes: Routes = [
  { path: '', redirectTo: 'form', pathMatch: 'full' },
  { path: 'form', component: ContractSearchComponent },
  { path: 'results', component: ContractResultsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SearchRoutingModule {}
