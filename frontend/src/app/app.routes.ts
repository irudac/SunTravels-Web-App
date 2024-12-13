import { Routes } from '@angular/router';

export const routes: Routes = [
  { 
    path: '', 
    loadComponent: () => import('./pages/homepage/homepage.component').then(m => m.HomepageComponent) 
  },
  {
    path: 'contracts',
    children: [
      {
        path: 'view',
        loadComponent: () =>
          import('./features/contracts/contract-view/contract-view.component')
            .then(m => m.ContractViewComponent)
      },
      {
        path: 'view/:id',
        loadComponent: () =>
          import('./features/contracts/contract-view/contract-view.component')
            .then(m => m.ContractViewComponent)
      },
      {
        path: 'load',
        loadComponent: () =>
          import('./features/contracts/contract-load/contract-load.component')
            .then(m => m.ContractLoadComponent)
      },
      {
        path: 'edit/:id',
        loadComponent: () =>
          import('./features/contracts/contract-edit/contract-edit.component')
            .then(m => m.ContractEditComponent)
      },
      { path: '', redirectTo: 'view', pathMatch: 'full' }
    ]
  },
  {
    path: 'search',
    children: [
      {
        path: 'form',
        loadComponent: () =>
          import('./features/search/contract-search/contract-search.component')
            .then(m => m.ContractSearchComponent)
      },
      {
        path: 'results',
        loadComponent: () =>
          import('./features/search/contract-results/contract-results.component')
            .then(m => m.ContractResultsComponent)
      },
      { path: '', redirectTo: 'form', pathMatch: 'full' }
    ]
  }  
];
