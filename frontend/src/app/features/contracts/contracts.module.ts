import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContractLoadComponent } from './contract-load/contract-load.component';
import { ContractViewComponent } from './contract-view/contract-view.component';

const routes: Routes = [
  { path: '', redirectTo: 'view', pathMatch: 'full' },
  { path: 'load', component: ContractLoadComponent },
  { path: 'view', component: ContractViewComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContractsRoutingModule {}
