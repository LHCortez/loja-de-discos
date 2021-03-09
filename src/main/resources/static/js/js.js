function confirmDeleteBand(event, id, nome) {
    event.preventDefault();
    Swal.fire({
        title: ("Deletar " + nome + "?"),
        text: "Isto fará com que todos os produtos relacionados à banda também sejam deletados.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: '#499927',
        cancelButtonColor: '#d33',
        cancelButtonText: 'Não, cancelar',
        confirmButtonText: 'Sim, deletar'
    }).then((result) => {
        if (result.isConfirmed) {
            jQuery("#delete-band-" + id).submit();
        }
    });
}

function confirmDeleteProduct(event, id, nome) {
    event.preventDefault();
    Swal.fire({
        title: "Deletar produto?",
        text: ("Tem certeza que deseja deletar o produto " + nome + "?"),
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: '#499927',
        cancelButtonColor: '#d33',
        cancelButtonText: 'Não, cancelar',
        confirmButtonText: 'Sim, deletar'
    }).then((result) => {
        if (result.isConfirmed) {
            jQuery("#delete-product-" + id).submit();
        }
    });

}
