import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { AuthService } from '../../services/auth.service'; // <--- Importa tu servicio (ajusta la ruta según tu estructura)
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  
  loginForm!: FormGroup;
  currentLang: string = 'es';
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private translate: TranslateService,
    private authService: AuthService,
    private router: Router
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
    this.authService.login(this.loginForm.value).subscribe({
      next: (response) => {
        console.log('¡Login exitoso!', response);
        localStorage.setItem('authToken', response.token);

        this.successMessage = '¡Inicio de sesión correcto! Redirigiendo...';
        setTimeout(() => {
          window.location.reload(); 
        }, 1000);
      },
      error: (err) => {
        console.error('Error en el login:', err);
        
        if (err.error && err.error.message) {
          this.errorMessage = err.error.message; 
        } else {
          this.errorMessage = 'Ocurrió un error inesperado en el servidor.';
        }
      }
    });
  }
}

  onLoginSSO(): void {
    console.log('SSO...');
  }
}