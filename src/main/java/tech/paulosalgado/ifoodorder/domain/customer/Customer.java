package tech.paulosalgado.ifoodorder.domain.customer;

import br.com.safeguard.check.SafeguardCheck;
import br.com.safeguard.types.ParametroTipo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerCreationException;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Customer {

    @Id
    private UUID customerId;

    private String name;
    private String cpf;

    public static class Builder {

        private UUID customerId;
        private String name;
        private String cpf;

        public Builder() {
            this.customerId = UUID.randomUUID();
        }

        public Customer.Builder withCustomerId(UUID id) throws CustomerCreationException {

            if (id == null) {
                throw new CustomerCreationException("customerId must be valid");
            }

            this.customerId = id;
            return this;
        }

        public Customer.Builder withName(String name) throws CustomerCreationException {

            if (name == null || name.isEmpty()) {
                throw new CustomerCreationException("name must be valid");
            }

            this.name = name;
            return this;
        }

        public Customer.Builder withCpf(String cpf) throws CustomerCreationException {

            if (cpf == null || cpf.isEmpty() ||
                    new SafeguardCheck().elementOf(cpf, ParametroTipo.CPF).validate().hasError()) {

                throw new CustomerCreationException("cpf must be valid");
            }

            this.cpf = cpf;
            return this;
        }

        public Customer build() {
            return new Customer(this.customerId, this.name, this.cpf);
        }

    }

}
