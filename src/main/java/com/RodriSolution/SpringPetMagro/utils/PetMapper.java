package com.RodriSolution.SpringPetMagro.utils;

import com.RodriSolution.SpringPetMagro.model.dtos.PetResponsedDto;
import com.RodriSolution.SpringPetMagro.model.dtos.PetResquestDto;
import com.RodriSolution.SpringPetMagro.model.entities.Pet;
import com.RodriSolution.SpringPetMagro.model.entities.Tutor;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {
    public static Pet toEntity(PetResquestDto RequestDto, Tutor tutor) {
        if(RequestDto == null){
            throw  new IllegalArgumentException("Informações  que foram passadas estao erradas");
        }
        Pet pet = new Pet();
        pet.setTipo(RequestDto.tipo());
        pet.setPetNome(RequestDto.petNome());
        pet.setLastnamePet(RequestDto.lastnamePet());
        pet.setSexo(RequestDto.sexo());
        pet.setEndereco(RequestDto.endereco());
        pet.setIdade(RequestDto.idade());
        pet.setPeso(RequestDto.peso());
        pet.setRaca(RequestDto.raca());
        pet.setTutor(tutor);
        pet.setData(RequestDto.data());
        return pet;
    }

    public static PetResponsedDto toDto(Pet pet) {
        return  new PetResponsedDto(
                pet.getId(),
                pet.getTipo(),
                pet.getPetNome(),
                pet.getLastnamePet(),
                pet.getSexo(),
                pet.getEndereco(),
                pet.getIdade(),
                pet.getPeso(),
                pet.getRaca(),
                TutorMapper.toDto(pet.getTutor()),
                pet.getData()
        );
    }
}
