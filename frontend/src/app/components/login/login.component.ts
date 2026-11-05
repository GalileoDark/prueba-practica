import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  
  loginForm!: FormGroup;
  currentLang: string = 'es';

  constructor(
    private formBuilder: FormBuilder,
    private translate: TranslateService
  ) { 
    this.translate.setDefaultLang('es');
    this.translate.use('es');
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onLanguageChange(event: any): void {
    const selectedLang = event.target.value;
    this.currentLang = selectedLang;
    this.translate.use(selectedLang);
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      console.log('Datos:', this.loginForm.value);
    }
  }

  onLoginSSO(): void {
    console.log('SSO...');
  }
}