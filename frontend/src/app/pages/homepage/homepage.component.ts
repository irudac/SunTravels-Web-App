import { Component } from '@angular/core';
import { AnimatedTextComponent } from '../../shared/animated-text/animated-text.component';
import { AnimatedScrollTextComponent } from '../../shared/animated-scroll-text/animated-scroll-text.component';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-homepage',
  imports: [AnimatedTextComponent, AnimatedScrollTextComponent, RouterLink],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss'
})
export class HomepageComponent {

}